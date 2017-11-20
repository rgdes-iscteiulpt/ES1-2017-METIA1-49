package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GeneticAlgorithmBuilder.GeneticAlgorithmVariant;

public class GUI {

	private JFrame frame;
	private JTable table;
	private JPanel scrollPanel;
	private JPanel buttonPanel;
	private JButton resultButton;
	private JButton saveButton;

	private FileReader r;
	// private ArrayList<Rule> rules;
	private String[][] data;

	public GUI() {
		r = new FileReader();
		// this.rules = r.getRules();
		createTable(r.getRules());
		init();

	}

	// Cria a Janela de Regras/Pesos

	public void init() {

		frame = new JFrame("Anti-Spam Filter Manual");
		frame.setLayout(new BorderLayout());

		// Acrescentar o scroll � tabela
		scrollPanel = new JPanel();
		scrollPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rules List"));

		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.add(scroll);

		// Acrescentar ao painel de bot�es os bot�es;
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		resultButton = new JButton("	Result	 ");

		saveButton = new JButton("	Save changes	");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int weight = 0;

				for (int i = 0; i < r.getRules().size(); i++) {
					String s = (String) table.getValueAt(i, 1);
					if (s == null) {
						s = "0";
					}
					weight = Integer.parseInt(s);
					System.out.println(s);
					r.getRules().get(i).setWeight(weight);
				}
				checkWeights();
			}
		});

		buttonPanel.add(resultButton);
		buttonPanel.add(saveButton);

		// Acrescentar os paineis � frame;
		frame.add(buttonPanel, BorderLayout.EAST);
		frame.add(scrollPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// Cria a tabela correspondente a Janela

	public void createTable(ArrayList<Rule> rules) {
		String[] columns = { "Rules", "Weights" };
		data = new String[r.getRules().size()][r.getRules().size()];

		for (int i = 0; i < rules.size(); i++) {

			// Matriz data[Regras][Pesos]

			data[i][0] = r.getRules().get(i).getName();

		}

		table = new JTable(data, columns) {

			// Tabela n�o edit�vel na coluna das regras;
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			};
		};

	}

	public void checkWeights() {
		for (int j = 0; j < r.getRules().size(); j++) {
			System.out.println(r.getRules().get(j).getName() + " - " + r.getRules().get(j).getWeight());

		}
	}

}
