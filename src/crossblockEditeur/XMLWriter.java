package crossblockEditeur;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLWriter {

	public XMLWriter(PanelEditor panel){
		try{
			String NbBlocksToDestroy = JOptionPane.showInputDialog(null,"Type in the exact number of blocks to cross to destroy them for this level", "Editor",JOptionPane.QUESTION_MESSAGE);
			//Test if the character string is a integer
			try {	
				Integer.parseInt(NbBlocksToDestroy);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null,"The input is not an integer");
				return;
			}
			panel.setNbBlocksToDestroy(NbBlocksToDestroy);
			//Creates an instance of class "DocumentBuilderFactory"
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try{
				//Creates a parser
				final DocumentBuilder builder = factory.newDocumentBuilder();
				final TransformerFactory transformerFactory;
				final Transformer transformer;
				final DOMSource source;
				final StreamResult output;

				final Document document;
				final Element levelElement;

				if (panel.getLevel() == 1){
					//Creates a document
					document= builder.newDocument();
					//Creates the root element
					final Element rootElement = document.createElement("Levels_CrossBlock");			
					rootElement.setAttribute("xsi:schemaLocation", "SchemaXML.xsd");
					rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "SchemaXML.xsd");
					rootElement.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
					document.appendChild(rootElement);
					//Creates the title
					final Element titleElement = document.createElement("Title");
					String title = JOptionPane.showInputDialog(null,"Please enter the title of the file XML\n'Default' by default", "Save",JOptionPane.QUESTION_MESSAGE);
					if (title==null || title.length()==0){
						title="default";
					} 
					titleElement.appendChild(document.createTextNode(title));
					rootElement.appendChild(titleElement);
					//Creates a summary
					final Element summaryElement = document.createElement("Summary");
					String summary = JOptionPane.showInputDialog(null,"Please enter a short summary for the XML file\n'Default' by default", "Save",JOptionPane.QUESTION_MESSAGE);
					if (summary==null || summary.length()==0){
						summary="default";
					}
					summaryElement.appendChild(document.createTextNode(summary));
					rootElement.appendChild(summaryElement);
					//Creates the list level
					final Element levelsListElement = document.createElement("Levels_List");
					levelsListElement.setAttribute("Max_Width", "11");
					levelsListElement.setAttribute("Max_Length", "20");
					rootElement.appendChild(levelsListElement);
					//Creates the level
					levelElement = document.createElement("Level");
				} else {
					document= builder.parse(new File("XML/"+panel.getSavingAddress()+".xml"));
					levelElement = document.createElement("Level");
				}
				levelElement.setAttribute("Identifier",""+panel.getLevel());
				levelElement.setAttribute("Number_Blocks_Destruction",panel.getNbBlocksToDestroy());
				levelElement.setAttribute("Width","11");
				levelElement.setAttribute("Length","20");
				//Getting the root element
				final Element rootElement = document.getDocumentElement();
				//Getting the levels list
				final Element levelsListElement = (Element) rootElement.getElementsByTagName("Levels_List").item(0);
				levelsListElement.appendChild(levelElement);
				int[][] Editor = panel.getEditor();
				for (int i=0;i<20;i++){
					for (int j=0;j<11;j++){
						if (Editor[i][j] == 1){
							final Element blockElement = document.createElement("Block");
							levelElement.appendChild(blockElement);
							final Element abscissaElement = document.createElement("Abscissa");
							abscissaElement.appendChild(document.createTextNode(""+i));
							blockElement.appendChild(abscissaElement);
							final Element ordinateElement = document.createElement("Ordinate");
							ordinateElement.appendChild(document.createTextNode(""+j));
							blockElement.appendChild(ordinateElement);
						}
					}
				}
				transformerFactory = TransformerFactory.newInstance();
				transformer = transformerFactory.newTransformer();
				source = new DOMSource(document);
				if (panel.getSavingAddress()==null){
					panel.setSavingAddress(JOptionPane.showInputDialog(null,"Please enter a title for the XML file\n'Default' by default", "Save",JOptionPane.QUESTION_MESSAGE));
				}
				if (panel.getSavingAddress()==null){
					//If the user pressed 'Cancel' or on the cross button on the dialog box "Please enter a title for the XML file"
					panel.setSavingAddress("default");
				} else if (panel.getSavingAddress().length()==0){
					//If the user did not type in any character "Please enter a title for the XML file"
					panel.setSavingAddress("default");
				}
				output = new StreamResult(new File("XML/"+panel.getSavingAddress()+".xml"));
				//Prolog
				transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.STANDALONE, "yes"); 

				//Formating
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

				//Output
				transformer.transform(source, output);
				panel.setLevel(panel.getLevel()+1);;
			}
			catch (final ParserConfigurationException e) {
				e.printStackTrace();
			}
			catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
			catch (TransformerException e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
