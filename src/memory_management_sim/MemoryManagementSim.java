package memory_management_sim;

import java.io.*;
import java.util.*;

public class MemoryManagementSim {
	private static LinkedList<MemorySlot> slots;
	private static Process[] processes;

	public static void main(String[] args) throws FileNotFoundException {		
		initializeSlots();
		initializeProcesses();
		
		firstFitAlgorithm();	
		bestFitAlgorithm();
		worstFitAlgorithm();
		
	}	
	
	private static void initializeSlots() throws FileNotFoundException {
		File memFile = new File("Minput.data");		
		Scanner memScan = new Scanner(memFile);
		slots = new LinkedList<MemorySlot>();
		memScan.nextLine();
		while(memScan.hasNextInt()){
			int begin = memScan.nextInt();
			int end = memScan.nextInt();
			slots.add(new MemorySlot(begin, end));
		}
		memScan.close();
	}
	
	private static void initializeProcesses() throws FileNotFoundException {
		File procFile = new File("Pinput.data");
		Scanner procScan = new Scanner(procFile);
		int noOfProcesses = procScan.nextInt();
		processes = new Process[noOfProcesses];
		int nextIndex = 0;
		while(procScan.hasNextInt()){
			int id = procScan.nextInt();
			int size = procScan.nextInt();
			processes[nextIndex] = new Process(id, size);
			nextIndex++;
		}
		procScan.close();
	}
	
	private static void firstFitAlgorithm() throws FileNotFoundException {
		LinkedList<MemorySlot> firstFitSlots = copy(slots);
		for(Process p : processes){
			int index = 0;
			while(index < firstFitSlots.size()){
				MemorySlot slot = firstFitSlots.get(index);
				int slotSize = slot.end - slot.begin;
				if(slotSize >= p.size && slot.process == null){
					int newEnd = slot.begin + p.size;
					int oldEnd = slot.end;
					int newBegin = newEnd + 1;
					slot.end = newEnd;
					slot.process = p;
					firstFitSlots.add(index+1, new MemorySlot(newBegin, oldEnd));
					break;
				}
				index++;
			}
		}
		
		printOutcome(firstFitSlots, "FFoutput.data");
	}
	
	private static void bestFitAlgorithm() throws FileNotFoundException {
		LinkedList<MemorySlot> bestFitSlots = copy(slots);
		for(Process p : processes){
			int index = 0;
			int bestFit = -1;
			while(index < bestFitSlots.size()){
				MemorySlot slot = bestFitSlots.get(index);
				int slotSize = slot.size();
				if(slotSize >= p.size && slot.process == null){
					if(bestFit == -1){
						bestFit = index;
					} else if(slot.size() < bestFitSlots.get(bestFit).size()){
						bestFit = index;
					}					
				}
				index++;
			}
			if (bestFit != -1){				
				MemorySlot bestSlot = bestFitSlots.get(bestFit);
				int newEnd = bestSlot.begin + p.size;
				int oldEnd = bestSlot.end;
				int newBegin = newEnd + 1;
				bestSlot.end = newEnd;
				bestSlot.process = p;
				bestFitSlots.add(bestFit+1, new MemorySlot(newBegin, oldEnd));				
			}
			//System.out.println(bestFit);
		}
		printOutcome(bestFitSlots, "BFoutput.data");
	}
	
	private static void worstFitAlgorithm() throws FileNotFoundException{
		LinkedList<MemorySlot> worstFitSlots = copy(slots);
		for(Process p : processes){
			int index = 0;
			int bestFit = -1;
			while(index < worstFitSlots.size()){
				MemorySlot slot = worstFitSlots.get(index);
				int slotSize = slot.size();
				if(slotSize >= p.size && slot.process == null){
					if(bestFit == -1){
						bestFit = index;
					} else if(slot.size() > worstFitSlots.get(bestFit).size()){
						bestFit = index;
					}					
				}
				index++;
			}
			if (bestFit != -1){				
				MemorySlot bestSlot = worstFitSlots.get(bestFit);
				int newEnd = bestSlot.begin + p.size;
				int oldEnd = bestSlot.end;
				int newBegin = newEnd + 1;
				bestSlot.end = newEnd;
				bestSlot.process = p;
				worstFitSlots.add(bestFit+1, new MemorySlot(newBegin, oldEnd));				
			}
			//System.out.println(bestFit);
		}
		printOutcome(worstFitSlots, "WFoutput.data");	
	}

	private static LinkedList<MemorySlot> copy(LinkedList<MemorySlot> list){
		LinkedList<MemorySlot> copy = new LinkedList<MemorySlot>();
		for (MemorySlot m : list){
			MemorySlot newElement = new MemorySlot(m.begin, m.end);
			copy.add(newElement);
		}
		
		return copy;
	}

	private static void printOutcome(LinkedList<MemorySlot> slots, String fileName) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(fileName);
		for(MemorySlot m : slots){
			if(m.process != null){
				writer.println(m.begin + " " + m.end + " " + m.process.id);				
			}
		}		
		writer.print("-");
		ArrayList<Process> notInList = new ArrayList<Process>();
		for(Process p : processes){
			if(!isInList(slots, p)){
				writer.print(p.id + " ");
				notInList.add(p);
			}
			
		}
		if(notInList.size() == 0){
			writer.print("0");
		}
		writer.close();
		
	}	
	
	private static boolean isInList(LinkedList<MemorySlot> list, Process p){
		for(MemorySlot m : list){
			if(m.process == p){
				return true;
			}
		}
		return false;
	}
}


