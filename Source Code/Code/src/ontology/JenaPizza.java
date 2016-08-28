package ontology;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.SomeValuesFromRestriction;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.XSD;


public class JenaPizza {
	public static void main(String args[]){
		String uriBase = "https://www.kdm.com/Jena/pizza";
		String NS = uriBase + "#";
		OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		base.createOntology(uriBase);	
		

		//Creating Classes
		OntClass Pizza = base.createClass(NS+"Pizza");
		OntClass PizzaTopping = base.createClass(NS+"PizzaTopping");
		OntClass PizzaBase = base.createClass(NS+"PizzaBase");

		//Making classes disjoint with each other
		Pizza.addDisjointWith(PizzaBase);
		Pizza.addDisjointWith(PizzaTopping);
		
		//Creating Subclasses for PizzaBase class
		OntClass ThinAndCrisyBase = base.createClass(NS+"ThinAndCrisyBase");
		OntClass DeepPanBase = base.createClass(NS+"DeepPanBase");
		PizzaBase.addSubClass(ThinAndCrisyBase);
		PizzaBase.addSubClass(DeepPanBase);
		
		//Creating Subclasses for PizzaTopping class
		OntClass MeatTopping = base.createClass(NS+"MeatTopping");
		OntClass VegetableTopping = base.createClass(NS+"VegetableTopping");
		OntClass CheeseTopping = base.createClass(NS+"CheeseTopping");
		OntClass SeafoodTopping = base.createClass(NS+"SeafoodTopping");
		PizzaTopping.addSubClass(MeatTopping);
		PizzaTopping.addSubClass(VegetableTopping);
		PizzaTopping.addSubClass(CheeseTopping);
		PizzaTopping.addSubClass(SeafoodTopping);
		
		//Creating Subclasses for MeatTopping class
		OntClass SpicyBeefTopping = base.createClass(NS+"SpicyBeefTopping");
		OntClass PepperoniTopping = base.createClass(NS+"PepperoniTopping");
		OntClass SalamiTopping = base.createClass(NS+"SalamiTopping");
		OntClass HamTopping = base.createClass(NS+"HamTopping");
		MeatTopping.addSubClass(SpicyBeefTopping);
		MeatTopping.addSubClass(PepperoniTopping);
		MeatTopping.addSubClass(SalamiTopping);
		MeatTopping.addSubClass(HamTopping);
		
		//Creating Subclasses for VegetableTopping class
		OntClass TomatoTopping = base.createClass(NS+"TomatoTopping");
		OntClass OliveTopping = base.createClass(NS+"OliveTopping");
		OntClass MushroomTopping = base.createClass(NS+"MushroomTopping");
		OntClass PepperTopping = base.createClass(NS+"PepperTopping");
		OntClass OnionTopping = base.createClass(NS+"OnionTopping");
		OntClass CaperTopping = base.createClass(NS+"CaperTopping");
		VegetableTopping.addSubClass(TomatoTopping);
		VegetableTopping.addSubClass(OliveTopping);
		VegetableTopping.addSubClass(MushroomTopping);
		VegetableTopping.addSubClass(PepperTopping);
		VegetableTopping.addSubClass(OnionTopping);
		VegetableTopping.addSubClass(CaperTopping);
		
		//Creating Subclasses for PepperTopping class
		OntClass RedPepperTopping = base.createClass(NS+"RedPepperTopping");
		OntClass GreenPepperTopping = base.createClass(NS+"GreenPepperTopping");
		OntClass JalapenoPepperTopping = base.createClass(NS+"JalapenoPepperTopping");
		PepperTopping.addSubClass(RedPepperTopping);
		PepperTopping.addSubClass(GreenPepperTopping);
		PepperTopping.addSubClass(JalapenoPepperTopping);
		
		//Creating Subclasses for CheeseTopping class
		OntClass MozzarellaTopping = base.createClass(NS+"MozzarellaTopping");
		OntClass ParmezanTopping = base.createClass(NS+"ParmezanTopping");
		CheeseTopping.addSubClass(MozzarellaTopping);
		CheeseTopping.addSubClass(ParmezanTopping);
		
		//Creating Subclasses for SeafoodTopping class
		OntClass TunaTopping = base.createClass(NS+"TunaTopping");
		OntClass AnchovyTopping = base.createClass(NS+"AnchovyTopping");
		OntClass PrawnTopping = base.createClass(NS+"PrawnTopping");
		SeafoodTopping.addSubClass(TunaTopping);
		SeafoodTopping.addSubClass(AnchovyTopping);
		SeafoodTopping.addSubClass(PrawnTopping);
		
		//Creating Class Country and Individuals to classes
		OntClass Country = base.createClass(NS+"Country");
//		Individual India = base.createIndividual(NS+"India", Country);
//		Individual USA = base.createIndividual(NS+"USA", Country);
//		Individual UK = base.createIndividual(NS+"UK", Country);
	
		//Creating Food class
		OntClass Food = base.createClass(NS+"Food");
		
		//Creating Object Properties
		ObjectProperty isIngredientOf = base.createObjectProperty(NS+"isIngredientOf");
		isIngredientOf.setDomain(Food);
		isIngredientOf.setRange(Food);
		
		ObjectProperty hasIngredient = base.createObjectProperty(NS+"hasIngredient");
		hasIngredient.setDomain(Food);
		hasIngredient.setRange(Food);
		
		//Making isIngredientOf and hasIngredient inverse properties
		isIngredientOf.setInverseOf(hasIngredient);
		
		//Creating hasTopping, hasBase Properties
		ObjectProperty hasTopping = base.createObjectProperty(NS+"hasTopping");
		hasTopping.setDomain(Pizza);
		hasTopping.setRange(PizzaTopping);
		
		ObjectProperty hasBase = base.createObjectProperty(NS+"hasBase");
		hasBase.setDomain(Pizza);
		hasBase.setRange(PizzaBase);
		
		//Making hasBase property as Functional
		hasBase.convertToFunctionalProperty();
		
		//Making hasBase property as Transitive
		hasBase.convertToTransitiveProperty();
		
		//Creating isToppingOf, isBaseOf Properties
		ObjectProperty isToppingOf = base.createObjectProperty(NS+"isToppingOf");
		isToppingOf.setDomain(PizzaTopping);
		isToppingOf.setRange(Pizza);
		
		ObjectProperty isBaseOf = base.createObjectProperty(NS+"isBaseOf");
		isBaseOf.setDomain(PizzaBase);
		isBaseOf.setRange(Pizza);
		
		 
		//Making isToppingOf and hasTopping inverse properties
		isToppingOf.setInverseOf(hasTopping);
		
		//Making isBaseOf and hasBase inverse properties
		isBaseOf.setInverseOf(hasBase);
		
		//Creating Data property
		DatatypeProperty hasVarieties = base.createDatatypeProperty(NS+"hasVarieties");
		hasVarieties.addDomain(Country);
		hasVarieties.addRange(XSD.integer);
		
		//Some values from Restriction
		SomeValuesFromRestriction svfrPizza = base.createSomeValuesFromRestriction(null, hasBase, PizzaBase);
		svfrPizza.addSubClass(Pizza);
		
		
		//Creating different kind of pizzas
		OntClass NamedPizza = base.createClass(NS+"NamedPizza");
		Pizza.addSubClass(NamedPizza);
		OntClass MargheritaPizza = base.createClass(NS+"MargheritaPizza");
		NamedPizza.addSubClass(MargheritaPizza);
		
		MargheritaPizza.addComment("A pizza that only has Mozarella and Tomato toppings", "en");
		
		SomeValuesFromRestriction svfrMargheritaPizzaMozzarella = base.createSomeValuesFromRestriction(null, hasTopping, MozzarellaTopping);
		svfrMargheritaPizzaMozzarella.addSubClass(MargheritaPizza);
		
		SomeValuesFromRestriction svfrMargheritaPizzaTomato = base.createSomeValuesFromRestriction(null, hasTopping, TomatoTopping);
		svfrMargheritaPizzaTomato.addSubClass(MargheritaPizza);
		
//		base.write(System.out, "RDF/XML-ABBREV");
		
		String fileName = "Output/JenaPizza.owl";
		try {
			FileWriter out = new FileWriter( fileName );
			base.write(out, "RDF/XML-ABBREV");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ontology Created");
	}
	
}
