package bentleyhoang.test;

import bentleyhoang.com.control.KMaxWeightPathsAlg;
import bentleyhoang.com.model.Graph;
import bentleyhoang.com.model.State;
import bentleyhoang.com.model.Vertex;

public class Test {
	public static void main(String[] args) {
		String dataFilePath = "C://Users//hoang//Documents//workspace//TestCasesGenerator//src//bentleyhoang//test//LTS.txt";
		String resultFilePath = "C://Users//hoang//Documents//workspace//TestCasesGenerator//src//bentleyhoang//test//TC.txt";
		
		Graph graph = new Graph(dataFilePath);
		KMaxWeightPathsAlg alg = new KMaxWeightPathsAlg(graph);
		Vertex v0 = new Vertex(new State("S0", 0, 0));
		Vertex v5 = new Vertex(new State("S5", 2, 10));
		
		alg.findPathsHaveKMaxLength(v0, v5, 8, resultFilePath);
	}
}
