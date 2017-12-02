package crossblockJeu;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

	public XMLReader(Level level, int numLevel, String address){
		//Creates an instance of class "DocumentBuilderFactory"
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();            	

		try {
			//Creates a parser object
			final DocumentBuilder builder = factory.newDocumentBuilder();

			//Creates a document object
			final Document document= builder.parse(new File("XML/"+address+".xml"));

			//Prints the prolog
			System.out.println("*************PROLOG************");
			System.out.println("version : " + document.getXmlVersion());
			System.out.println("encoding : " + document.getXmlEncoding());		
			System.out.println("standalone : " + document.getXmlStandalone());
			System.out.println("*********************************");

			//Getting the root element
			final Element rootElement = document.getDocumentElement();

			//Getting the levels list
			Element levelsListElement = (Element) rootElement.getElementsByTagName("Levels_List").item(0);

			//Getting the level information
			NodeList levelsElement = levelsListElement.getElementsByTagName("Level");
			int nbLevels = levelsElement.getLength();
			if (nbLevels >= numLevel){
				Element levelElement = (Element) levelsElement.item(numLevel-1);
				level.setNumLevel(Integer.parseInt(levelElement.getAttribute("Identifier")));;
				level.setNumberBlocks(Integer.parseInt(levelElement.getAttribute("Number_Blocks_Destruction")));
				//int width = Integer.parseInt(niveauElement.getAttribute("Width"));
				//int length = Integer.parseInt(niveauElement.getAttribute("Length"));

				//Getting the blocks
				NodeList blocks = levelElement.getElementsByTagName("Block");
				int nbBlocks = blocks.getLength();
				ArrayList<Block> remainingBlocks = new ArrayList<Block>();
				for (int j = 0; j<nbBlocks; j++){
					Element blockElement = (Element) blocks.item(j);

					//Getting the abscissa
					Element abscissaElement = (Element) blockElement.getElementsByTagName("Abscissa").item(0);
					int x = Integer.parseInt(abscissaElement.getTextContent());

					//Getting the ordinate
					Element ordinateElement = (Element) blockElement.getElementsByTagName("Ordinate").item(0);
					int y = Integer.parseInt(ordinateElement.getTextContent());

					//Calls the block constructor and adds the blocks in the ArrayList<Bloc> remainingBlocks
					Block Block = new Block (x*34,y*34);
					remainingBlocks.add(Block);
				}
				level.setRemainingBlocks(remainingBlocks);
			}
		}
		catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (final SAXException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
		}
	}
}
