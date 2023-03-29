package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
	
	public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

		// leitura da imagem
		
		// InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
		// InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_2.jpg").openStream();
		BufferedImage imagemOriginal =  ImageIO.read(inputStream);
		

		// cria nova imagem com transparencia e tamanho novo
		
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT) {
		};
		

		// copiar a imagem original p nova imagem (em memoria)
		
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);
		

		// configurar a fonte
		
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 65);
		graphics.setColor(Color.CYAN);
		graphics.setFont(fonte);
		

		// escrever uma frase na nova imagem
		
		
		
		String texto = "MUITO MÍDIA";
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
		int larguraTexto = (int) retangulo.getWidth();
		int posicaoTextoX = (largura - larguraTexto) / 2;
		graphics.drawString(texto, posicaoTextoX, novaAltura - 100);


		// escrever a nova imagem em um arquivo
		
		ImageIO.write(novaImagem, "png", new File(nomeArquivo));
	}
	
}
