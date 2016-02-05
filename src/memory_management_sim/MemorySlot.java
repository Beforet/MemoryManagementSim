package memory_management_sim;

public class MemorySlot {
	public int begin;
	public int end;
	public Process process;
	
	public MemorySlot(int begin, int end){
		this.begin = begin;
		this.end = end;
		process = null;
	}
	
	public int size(){
		return end - begin;
	}
	
	public String toSting(){
		return "Begin: " + begin + "; " + "End: " + end;
	}
}
