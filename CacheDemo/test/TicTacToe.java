import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TicTacToe extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public TicTacToe() {
		GridLayout gl = new GridLayout(3, 3);
		setLayout(gl);

		for (int i = 0; i < 9; i++) {
			int mode = (int) (Math.random() * 3);
			if (mode == 0) {
				JLabel label = new JLabel(getCrossImage());
				label.setSize(new Dimension(100, 100));
				add(label);
			} else if (mode == 1) {
				JLabel label = new JLabel(getOImage());
				label.setSize(new Dimension(100, 100));
				add(label);
			} else {
				JLabel label = new JLabel(getBlankImage());
				label.setSize(new Dimension(100, 100));
				add(label);
			}
		}

	}

	private ImageIcon getCrossImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(this.getClass().getResource("x.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(image);
	}

	private ImageIcon getOImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(this.getClass().getResource("s_o.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(image);
	}
	
	private ImageIcon getBlankImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(this.getClass().getResource("blank.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ImageIcon(image);
	}

	public static void main(String[] args) {
		TicTacToe frame = new TicTacToe();
		frame.setLayout(new FlowLayout());
		frame.setTitle("TicTacToe");
		frame.setSize(new Dimension(600, 600));
		// frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);

	}
}