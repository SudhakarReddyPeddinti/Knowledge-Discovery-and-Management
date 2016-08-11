import org.apache.spark.mllib.feature.{HashingTF, IDF}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.immutable.HashMap

/**
  * Created by Mayanka on 15-Jun-16.
  */
object TFIDF_Main {
  def main(args: Array[String]) {

    //System.setProperty("hadoop.home.dir", "F:\\winutils");

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    val documents = sc.textFile("Article.txt")

   // val textFile = sc.textFile("hdfs://...")
    val counts = documents.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.foreach(f=>{
      println(f)
    })



    //--------
    val documentseq = documents.map(_.split(" ").toSeq)

    val strData = sc.broadcast(documentseq.collect())
    val hashingTF = new HashingTF()
    val tf = hashingTF.transform(documentseq)
    tf.cache()

    val tfvalues = tf.flatMap(f => {
      val ff: Array[String] = f.toString.replace(",[", ";").split(";")
      val values = ff(2).replace("]", "").replace(")","").split(",")
      values
    })

    val tfindex = tf.flatMap(f => {
      val ff: Array[String] = f.toString.replace(",[", ";").split(";")
      val indices = ff(1).replace("]", "").replace(")","").split(",")
      indices
    })


    val tfData = tfindex.zip(tfvalues)
    var hashMap = new HashMap[String, Double]
    tfData.collect().foreach(f => {
      hashMap += f._1 -> f._2.toDouble
    })
    val mapp_tf = sc.broadcast(hashMap)

    val documentData = documentseq.flatMap(_.toList)
    val dd_data = documentData.map(f => {
      val i = hashingTF.indexOf(f)
      val h = mapp_tf.value
      (f, h(i.toString))
    })

    val dd_tf=dd_data.distinct().sortBy(_._2,false)
    println("Top 20 words in TF: ")
    dd_tf.take(20).foreach(f=>{
      println(f)
    })

    println("IDF Beigns")


    // -------------------------------------------- TF-IDF


    println("idf begins:")
    println("idf ends!")
    val idf = new IDF().fit(tf)
    val tfidf = idf.transform(tf)

    val tfidfvalues = tfidf.flatMap(f => {
      val ff: Array[String] = f.toString.replace(",[", ";").split(";")
      val values = ff(2).replace("]", "").replace(")","").split(",")
      values
    })
    val tfidfindex = tfidf.flatMap(f => {
      val ff: Array[String] = f.toString.replace(",[", ";").split(";")
      val indices = ff(1).replace("]", "").replace(")","").split(",")
      indices
    })
    tfidf.foreach(f => println(f))

    val tfidfData = tfidfindex.zip(tfidfvalues)

    var hm = new HashMap[String, Double]
    tfidfData.collect().foreach(f => {
      hm += f._1 -> f._2.toDouble
    })
    val mapp = sc.broadcast(hm)

    //val documentData = documentseq.flatMap(_.toList)
    val dd = documentData.map(f => {
      val i = hashingTF.indexOf(f)
      val h = mapp.value
      (f, h(i.toString))
    })

    val dd1=dd.distinct().sortBy(_._2,false)
    println("Total Word Count in this document: "+ dd1.count())
    dd1.take(20).foreach(f=>{
      println(f)
    })
  }

}
