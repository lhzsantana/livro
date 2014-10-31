package devmedia.es_hadoop;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSSReader {

	private static Logger logger = Logger.getLogger(RSSReader.class);

	public static void main(String argv[]) {

		try {
			URL url = new URL(
					"http://globoesporte.globo.com/servico/semantica/editorias/plantao/futebol/times/santos/feed.rss");

			InputStream in = url.openStream();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(in);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");

			List<String> linhas = new ArrayList<String>();

			String linha = "";

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					linha += eElement.getElementsByTagName("title").item(0)
							.getTextContent()+"|";
					linha += eElement.getElementsByTagName("description").item(0)
							.getTextContent()+"|";
					linha += eElement.getElementsByTagName("link").item(0)
							.getTextContent()+"|";
					linha += eElement.getElementsByTagName("category").item(0)
							.getTextContent();
					
					linhas.add(linha);
				}
			}
			
			write(linhas);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void write(List<String> linhas) throws IOException {

		Path pt=new Path("hdfs://localhost:54310/devmedia/input.txt");
        FileSystem fs = FileSystem.get(new Configuration());
        
        BufferedWriter br=new BufferedWriter(new OutputStreamWriter(fs.create(pt,true)));
        
		for(String linha:linhas){			
			logger.info(linha);
            br.write(linha);         
		}
		
        br.close();	
	}
}
