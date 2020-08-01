package Interfaces;

public interface MatrixHVV {
	
	int vertToInd(Vertex v);
	
	Vertex indToVert(int x, int y);
	
	int getValM(int x, int y);
	
	void setValM(int x, int y, int value);

}
