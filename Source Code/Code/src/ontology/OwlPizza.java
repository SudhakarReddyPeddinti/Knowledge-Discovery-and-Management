package ontology;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

public class OwlPizza {
	public static void main(String args[])throws Exception{
		 OWLOntologyManager manager = OWLManager.createOWLOntologyManager(); //creating ontology manager
		 OWLDataFactory df = manager.getOWLDataFactory(); //In order to create objects that represent entities we need a

		 
		 OWLOntology ontology = manager.createOntology(IRI.create("https://www.kdm.com/OWL/", "pizza"));
		 PrefixManager pm = new DefaultPrefixManager(null, null,
		            "https://www.kdm.com/OWL/pizza#");
		 
		 OWLClass Pizza = df.getOWLClass(":Pizza", pm);
		 OWLClass PizzaTopping  = df.getOWLClass(":PizzaTopping", pm);
		 OWLClass PizzaBase = df.getOWLClass(":PizzaBase", pm);
		 
		 OWLDeclarationAxiom declarationAxiomPizza = df.getOWLDeclarationAxiom(Pizza);
		 OWLDeclarationAxiom declarationAxiomPizzaTopping = df.getOWLDeclarationAxiom(PizzaTopping);
		 OWLDeclarationAxiom declarationAxiomPizzaBase = df.getOWLDeclarationAxiom(PizzaBase);
		 
		 manager.addAxiom(ontology, declarationAxiomPizza);
		 manager.addAxiom(ontology, declarationAxiomPizzaTopping);
		 manager.addAxiom(ontology, declarationAxiomPizzaBase);
		 
		 //Making all classes Disjoint to each other
		 OWLDisjointClassesAxiom disjointClassesAxiom = df.getOWLDisjointClassesAxiom(Pizza, PizzaTopping, PizzaBase);
		 manager.addAxiom(ontology, disjointClassesAxiom);
		 
		 //Creating Subclasses for PizzaBase class
		 OWLClass ThinAndCrisyBase = df.getOWLClass(":ThinAndCrisyBase", pm);
		 OWLClass DeepPanBase = df.getOWLClass(":DeepPanBase", pm);
		 OWLSubClassOfAxiom declarationAxiomThinAndCrisyBase = df.getOWLSubClassOfAxiom(ThinAndCrisyBase, PizzaBase);
		 OWLSubClassOfAxiom declarationAxiomDeepPanBase = df.getOWLSubClassOfAxiom(DeepPanBase, PizzaBase);
		 manager.addAxiom(ontology, declarationAxiomThinAndCrisyBase);
		 manager.addAxiom(ontology, declarationAxiomDeepPanBase);

		//Creating Subclasses for PizzaTopping class
		 OWLClass MeatTopping = df.getOWLClass(":MeatTopping", pm);
		 OWLClass VegetableTopping = df.getOWLClass(":VegetableTopping", pm);
		 OWLClass CheeseTopping = df.getOWLClass(":CheeseTopping", pm);
		 OWLClass SeafoodTopping = df.getOWLClass(":SeafoodTopping", pm);
		 OWLSubClassOfAxiom declarationAxiomMeatTopping = df.getOWLSubClassOfAxiom(MeatTopping, PizzaTopping);
		 OWLSubClassOfAxiom declarationAxiomVegetableTopping = df.getOWLSubClassOfAxiom(VegetableTopping, PizzaTopping);
		 OWLSubClassOfAxiom declarationAxiomCheeseTopping = df.getOWLSubClassOfAxiom(CheeseTopping, PizzaTopping);
		 OWLSubClassOfAxiom declarationAxiomSeafoodTopping = df.getOWLSubClassOfAxiom(SeafoodTopping, PizzaTopping);
		 manager.addAxiom(ontology, declarationAxiomMeatTopping);
		 manager.addAxiom(ontology, declarationAxiomVegetableTopping);
		 manager.addAxiom(ontology, declarationAxiomCheeseTopping);
		 manager.addAxiom(ontology, declarationAxiomSeafoodTopping);

		//Creating Subclasses for MeatTopping class
		 OWLClass SpicyBeefTopping = df.getOWLClass(":SpicyBeefTopping", pm);
		 OWLClass PepperoniTopping = df.getOWLClass(":PepperoniTopping", pm);
		 OWLClass SalamiTopping  = df.getOWLClass(":SalamiTopping", pm);
		 OWLClass HamTopping = df.getOWLClass(":HamTopping", pm);
		 OWLSubClassOfAxiom declarationAxiomSpicyBeefTopping = df.getOWLSubClassOfAxiom(SpicyBeefTopping, MeatTopping);
		 OWLSubClassOfAxiom declarationAxiomPepperoniTopping = df.getOWLSubClassOfAxiom(PepperoniTopping, MeatTopping);
		 OWLSubClassOfAxiom declarationAxiomSalamiTopping= df.getOWLSubClassOfAxiom(SalamiTopping, MeatTopping);
		 OWLSubClassOfAxiom declarationAxiomHamTopping = df.getOWLSubClassOfAxiom(HamTopping, MeatTopping);
		 manager.addAxiom(ontology, declarationAxiomSpicyBeefTopping);
		 manager.addAxiom(ontology, declarationAxiomPepperoniTopping);
		 manager.addAxiom(ontology, declarationAxiomSalamiTopping);
		 manager.addAxiom(ontology, declarationAxiomHamTopping);
		 
		//Creating Subclasses for VegetableTopping class
		 OWLClass TomatoTopping = df.getOWLClass(":TomatoTopping", pm);
		 OWLClass OliveTopping = df.getOWLClass(":OliveTopping", pm);
		 OWLClass MushroomTopping  = df.getOWLClass(":MushroomTopping", pm);
		 OWLClass PepperTopping = df.getOWLClass(":PepperTopping", pm);
		 OWLClass OnionTopping  = df.getOWLClass(":OnionTopping", pm);
		 OWLClass CaperTopping = df.getOWLClass(":CaperTopping", pm);
		 OWLSubClassOfAxiom declarationAxiomSpicyTomatoTopping = df.getOWLSubClassOfAxiom(TomatoTopping, VegetableTopping);
		 OWLSubClassOfAxiom declarationAxiomOliveTopping = df.getOWLSubClassOfAxiom(OliveTopping, VegetableTopping);
		 OWLSubClassOfAxiom declarationAxiomMushroomTopping= df.getOWLSubClassOfAxiom(MushroomTopping, VegetableTopping);
		 OWLSubClassOfAxiom declarationAxiomPepperTopping = df.getOWLSubClassOfAxiom(PepperTopping, VegetableTopping);
		 OWLSubClassOfAxiom declarationAxiomOnionTopping = df.getOWLSubClassOfAxiom(OnionTopping, VegetableTopping);
		 OWLSubClassOfAxiom declarationAxiomCaperTopping = df.getOWLSubClassOfAxiom(CaperTopping, VegetableTopping);
		 manager.addAxiom(ontology, declarationAxiomSpicyTomatoTopping);
		 manager.addAxiom(ontology, declarationAxiomOliveTopping);
		 manager.addAxiom(ontology, declarationAxiomMushroomTopping);
		 manager.addAxiom(ontology, declarationAxiomPepperTopping);
		 manager.addAxiom(ontology, declarationAxiomOnionTopping);
		 manager.addAxiom(ontology, declarationAxiomCaperTopping);
		 
		//Creating Subclasses for PepperTopping class
		 OWLClass RedPepperTopping = df.getOWLClass(":RedPepperTopping", pm);
		 OWLClass GreenPepperTopping = df.getOWLClass(":GreenPepperTopping", pm);
		 OWLClass JalapenoPepperTopping  = df.getOWLClass(":JalapenoPepperTopping", pm);
		 OWLSubClassOfAxiom declarationAxiomRedPepperTopping = df.getOWLSubClassOfAxiom(RedPepperTopping, PepperTopping);
		 OWLSubClassOfAxiom declarationAxiomGreenPepperTopping = df.getOWLSubClassOfAxiom(GreenPepperTopping, PepperTopping);
		 OWLSubClassOfAxiom declarationAxiomJalapenoPepperTopping = df.getOWLSubClassOfAxiom(JalapenoPepperTopping, PepperTopping);
		 manager.addAxiom(ontology, declarationAxiomRedPepperTopping);
		 manager.addAxiom(ontology, declarationAxiomGreenPepperTopping);
		 manager.addAxiom(ontology, declarationAxiomJalapenoPepperTopping);
		 
		//Creating Subclasses for CheeseTopping class
		 OWLClass MozzarellaTopping = df.getOWLClass(":MozzarellaTopping", pm);
		 OWLClass ParmezanTopping  = df.getOWLClass(":ParmezanTopping", pm);
		 OWLSubClassOfAxiom declarationAxiomGreenMozzarellaTopping = df.getOWLSubClassOfAxiom(MozzarellaTopping, CheeseTopping);
		 OWLSubClassOfAxiom declarationAxiomJalapenoParmezanTopping = df.getOWLSubClassOfAxiom(ParmezanTopping, CheeseTopping);
		 manager.addAxiom(ontology, declarationAxiomGreenMozzarellaTopping);
		 manager.addAxiom(ontology, declarationAxiomJalapenoParmezanTopping);
		 
		//Creating Subclasses for SeafoodTopping class
		 OWLClass TunaTopping = df.getOWLClass(":TunaTopping", pm);
		 OWLClass AnchovyTopping  = df.getOWLClass(":AnchovyTopping", pm);
		 OWLClass PrawnTopping  = df.getOWLClass(":PrawnTopping", pm);
		 OWLSubClassOfAxiom declarationAxiomRedTunaTopping = df.getOWLSubClassOfAxiom(TunaTopping, SeafoodTopping);
		 OWLSubClassOfAxiom declarationAxiomAnchovyTopping  = df.getOWLSubClassOfAxiom(AnchovyTopping, SeafoodTopping);
		 OWLSubClassOfAxiom declarationAxiomPrawnTopping = df.getOWLSubClassOfAxiom(PrawnTopping, SeafoodTopping);
		 manager.addAxiom(ontology, declarationAxiomRedTunaTopping);
		 manager.addAxiom(ontology, declarationAxiomAnchovyTopping);
		 manager.addAxiom(ontology, declarationAxiomPrawnTopping);
		 
		 //Creating Class Country and Individuals to classes
		 OWLClass Country = df.getOWLClass(":Country", pm);
		 OWLDeclarationAxiom declarationAxiomCountry = df.getOWLDeclarationAxiom(Country);
		 OWLNamedIndividual India = df.getOWLNamedIndividual(":India", pm);
		 OWLNamedIndividual USA = df.getOWLNamedIndividual(":USA", pm);
		 OWLNamedIndividual UK = df.getOWLNamedIndividual(":UK", pm);
		//Class Assertion specifying India is member of Class Country
		 OWLClassAssertionAxiom classAssertionIndia = df.getOWLClassAssertionAxiom(Country, India);
		 OWLClassAssertionAxiom classAssertionUSA = df.getOWLClassAssertionAxiom(Country, USA);
		 OWLClassAssertionAxiom classAssertionUK = df.getOWLClassAssertionAxiom(Country, UK);
		 manager.addAxiom(ontology, declarationAxiomCountry);
		 manager.addAxiom(ontology, classAssertionIndia);
		 manager.addAxiom(ontology, classAssertionUSA);
		 manager.addAxiom(ontology, classAssertionUK);
		 
		 //Creating Food class
		 OWLClass Food = df.getOWLClass(":Food", pm);
		 OWLDeclarationAxiom declarationAxiomFood = df.getOWLDeclarationAxiom(Food);
		 manager.addAxiom(ontology, declarationAxiomFood);
		 
		 //Creating Object Properties
		 OWLObjectProperty isIngredientOf = df.getOWLObjectProperty(":isIngredientOf", pm);
		 OWLObjectPropertyRangeAxiom rangeAxiomisIngredientOf = df.getOWLObjectPropertyRangeAxiom(isIngredientOf, Food);
		 OWLObjectPropertyDomainAxiom domainAxiomisIngredientOf = df.getOWLObjectPropertyDomainAxiom(isIngredientOf, Food);
		 manager.addAxiom(ontology, rangeAxiomisIngredientOf);
		 manager.addAxiom(ontology, domainAxiomisIngredientOf);
		 
		 OWLObjectProperty hasIngredient = df.getOWLObjectProperty(":hasIngredient", pm);
		 OWLObjectPropertyRangeAxiom rangeAxiomhasIngredient = df.getOWLObjectPropertyRangeAxiom(hasIngredient, Food);
		 OWLObjectPropertyDomainAxiom domainAxiomhasIngredient = df.getOWLObjectPropertyDomainAxiom(hasIngredient, Food);
		 manager.addAxiom(ontology, rangeAxiomhasIngredient);
		 manager.addAxiom(ontology, domainAxiomhasIngredient);
		 
		 //Making isIngredientOf and hasIngredient inverse properties
		 manager.addAxiom(ontology, df.getOWLInverseObjectPropertiesAxiom(isIngredientOf, hasIngredient));
		 
		 //Creating hasTopping, hasBase Properties
		 OWLObjectProperty hasTopping = df.getOWLObjectProperty(":hasTopping", pm);
		 OWLObjectPropertyDomainAxiom domainAxiomhasTopping = df.getOWLObjectPropertyDomainAxiom(hasTopping, Pizza);
		 OWLObjectPropertyRangeAxiom rangeAxiomhasTopping = df.getOWLObjectPropertyRangeAxiom(hasTopping, PizzaTopping);
		 manager.addAxiom(ontology, rangeAxiomhasTopping);
		 manager.addAxiom(ontology, domainAxiomhasTopping);
		 manager.addAxiom(ontology, df.getOWLSubObjectPropertyOfAxiom(hasTopping, hasIngredient));
		 
		 OWLObjectProperty hasBase  = df.getOWLObjectProperty(":hasBase", pm);
		 OWLObjectPropertyDomainAxiom domainAxiomhasBase  = df.getOWLObjectPropertyDomainAxiom(hasBase, Pizza);
		 OWLObjectPropertyRangeAxiom rangeAxiomhasBase = df.getOWLObjectPropertyRangeAxiom(hasBase, PizzaBase);
		 manager.addAxiom(ontology, rangeAxiomhasBase);
		 manager.addAxiom(ontology, domainAxiomhasBase);
		 manager.addAxiom(ontology, df.getOWLSubObjectPropertyOfAxiom(hasBase , hasIngredient));
		 
		 //Making hasBase property as Functional
		 manager.addAxiom(ontology, df.getOWLFunctionalObjectPropertyAxiom(hasBase));
	
		 //Making hasBase property as Transitive
		 manager.addAxiom(ontology, df.getOWLTransitiveObjectPropertyAxiom(hasIngredient));
		 
		 //Creating isToppingOf, isBaseOf Properties
		 OWLObjectProperty isToppingOf = df.getOWLObjectProperty(":isToppingOf", pm);
		 OWLObjectPropertyDomainAxiom domainAxiomisToppingOf = df.getOWLObjectPropertyDomainAxiom(isToppingOf, PizzaTopping);
		 OWLObjectPropertyRangeAxiom rangeAxiomisToppingOf = df.getOWLObjectPropertyRangeAxiom(isToppingOf, Pizza);
		 manager.addAxiom(ontology, domainAxiomisToppingOf);
		 manager.addAxiom(ontology, rangeAxiomisToppingOf);
		 manager.addAxiom(ontology, df.getOWLSubObjectPropertyOfAxiom(isToppingOf, isIngredientOf));
		 
		 OWLObjectProperty isBaseOf = df.getOWLObjectProperty(":isBaseOf", pm);
		 OWLObjectPropertyDomainAxiom domainAxiomisBaseOf = df.getOWLObjectPropertyDomainAxiom(isBaseOf, PizzaBase);
		 OWLObjectPropertyRangeAxiom rangeAxiomisBaseOf = df.getOWLObjectPropertyRangeAxiom(isBaseOf, Pizza);
		 manager.addAxiom(ontology, domainAxiomisBaseOf);
		 manager.addAxiom(ontology, rangeAxiomisBaseOf);
		 manager.addAxiom(ontology, df.getOWLSubObjectPropertyOfAxiom(isBaseOf, isIngredientOf));
	
		 
		//Making isToppingOf and hasTopping inverse properties
		 manager.addAxiom(ontology, df.getOWLInverseObjectPropertiesAxiom(isToppingOf, hasTopping));
		 
		 //Making isBaseOf and hasBase inverse properties
		 manager.addAxiom(ontology, df.getOWLInverseObjectPropertiesAxiom(isBaseOf, hasBase));

		 
		 //Creating Data property
		 OWLDataProperty hasVarieties = df.getOWLDataProperty(":hasVarieties", pm);
		 OWLDatatype integerDatatype = df.getIntegerOWLDatatype();
		 OWLDataPropertyDomainAxiom domainAxiomhasVarieties = df.getOWLDataPropertyDomainAxiom(hasVarieties, Country);
	     OWLDataPropertyRangeAxiom rangeAxiomhasVarieties = df.getOWLDataPropertyRangeAxiom(hasVarieties, integerDatatype);
		 manager.addAxiom(ontology, domainAxiomhasVarieties);
		 manager.addAxiom(ontology, rangeAxiomhasVarieties);
	     
		 //Some values from Restriction
		 OWLClassExpression hasBaseRestriction = df.getOWLObjectSomeValuesFrom(hasBase, PizzaBase);
		 OWLSubClassOfAxiom ax = df.getOWLSubClassOfAxiom(Pizza, hasBaseRestriction);
		 manager.addAxiom(ontology, ax);
		 
		 //Creating different kind of pizzas
		 OWLClass NamedPizza = df.getOWLClass(":NamedPizza", pm);
		 OWLSubClassOfAxiom declarationAxiomNamedPizza = df.getOWLSubClassOfAxiom(NamedPizza, Pizza);
		 manager.addAxiom(ontology, declarationAxiomNamedPizza);
		 
		 OWLClass MargheritaPizza = df.getOWLClass(":MargheritaPizza", pm);
		 OWLSubClassOfAxiom declarationAxiomMargheritaPizza = df.getOWLSubClassOfAxiom(MargheritaPizza, NamedPizza);
		 manager.addAxiom(ontology, declarationAxiomMargheritaPizza);
		 
		 OWLAnnotation commentAnno = df.getRDFSComment(df.getOWLLiteral("A pizza that only has Mozarella and Tomato toppings", "en"));
		 OWLAxiom commentMargheritaPizza = df.getOWLAnnotationAssertionAxiom(MargheritaPizza.getIRI(), commentAnno);
		 manager.addAxiom(ontology, commentMargheritaPizza);
		 
		 OWLClassExpression hasToppingRestriction = df.getOWLObjectSomeValuesFrom(hasTopping, MozzarellaTopping );
		 OWLSubClassOfAxiom axiomhasToppingRestriction = df.getOWLSubClassOfAxiom(MargheritaPizza, hasToppingRestriction);
		 manager.addAxiom(ontology, axiomhasToppingRestriction);
		 		
		 OWLClassExpression hasToppingRestrictionTomato = df.getOWLObjectSomeValuesFrom(hasTopping, TomatoTopping );
		 OWLSubClassOfAxiom axiomhasToppingRestrictionTomato = df.getOWLSubClassOfAxiom(MargheritaPizza, hasToppingRestrictionTomato);
		 manager.addAxiom(ontology, axiomhasToppingRestrictionTomato);
		 		 
		 OutputStream os = new FileOutputStream("Output/OwlPizza.owl");
		 OWLXMLDocumentFormat owlxmlFormat = new OWLXMLDocumentFormat();
		 manager.saveOntology(ontology, owlxmlFormat, os);
		 System.out.println("Ontology Created");
	}
}
