/**
 * file: PatientQueue.java
 * class: CSC210
 * instructor: David Claveau
 * author: Aiden Foster
 * purpose: The purpose of this file is to implement a priority queue using an array
 */

public class PatientQueue {
	private int size;
	private Patient[] heap;
	private final static int ARRAY_SIZE = 10;
	
	
	/**
	 * This is the constructor for the priority queue
	 */
	public PatientQueue() {
		heap = new Patient[ARRAY_SIZE];
		heap[0] = null;
		size = 0;
	}
	
	/**
	 * This method allows us to compare to values and return whether it is true or false
	 * @param a is an object of patient
	 * @param b is an object of patient
	 * @return this will return true or false depending on if it is true or not
	 */
	public boolean compare(Patient a, Patient b) {
		if(a.priority != b.priority) 
			return a.priority < b.priority;
		return a.name.compareTo(b.name) < 0;
	}

	/**
	 * This method allows us to resize the priority queue to twice the size it was before hand
	 * @param capacity is the new capacity we will be making it
	 */
	private void resize(int capacity) {
		Patient[] temp = new Patient[capacity];
		size = capacity < size ? capacity:size;
		for (int i=0; i < size+1; i++)
			temp[i] = heap[i];
		heap = temp;
	}
	
	/**
	 * This method allows us to make a new patient object and call the other method
	 * @param name is the name of the patient
	 * @param priority is the priority of the patient 
	 */
	public void enqueue(String name, int priority) {
		Patient object = new Patient(name, priority);
		enqueue(object);
	}
	
	/**
	 * This method allows us to add the patient object to out queue
	 * @param patient is the object we will be adding 
	 */
	public void enqueue(Patient patient) {
		if(size >= heap.length-1) {
			resize(2*heap.length);
		}
		size++;
		heap[size] = patient;
		bubbleUp(size);
	}
	
	/**
	 * This method takes the most recently added patient and moves it up if need be
	 * @param i is where the patient we are moving is located
	 */
	public void bubbleUp(int i) {
		Patient p = heap[i];
		int parentIndex = parent(i);
		if(i > 1) {
			if(compare(heap[i], heap[parentIndex]) ) {
				Patient oldPatient =  heap[parentIndex];
				heap[parentIndex] = p;
				heap[i] = oldPatient;
				bubbleUp(parent(i));
			}
		}
	}
	
	/**
	 * This method allows us to remove the smallest priority on our queue
	 * @return this will return the name that we are getting rid of
	 */
	public String dequeue() {
		if(isEmpty())
			throw new NullPointerException();
		String name = heap[1].name;
		heap[1] = heap[size];
		heap[size] = null;
		size--;
		bubbleDown(1);
		return name;
	}
		
	/**
	 * This method takes the most recently added patient and moves it down if need be
	 * @param i is where the patient we are moving is located
	 */
	public void bubbleDown(int i) {
		if (left(i) <= size) {
			// set the higher priority child then check if we need to change it 
			int higherPriorityChild = left(i);
			if (right(i) <= size && compare(heap[right(i)], heap[left(i)])) {
				higherPriorityChild = right(i);
			}
			// check if we should bubble down
			if (compare(heap[higherPriorityChild],heap[i])) {
				Patient oldPatient = heap[higherPriorityChild];
				heap[higherPriorityChild] = heap[i];
				heap[i] = oldPatient;
				bubbleDown(higherPriorityChild);
			}
		}
	}
	
	/**
	 * This method allows us to look at the parent of the index we are looking at
	 * @param i is the index we are looking at
	 * @return is the parent location
	 */
	public int parent(int i) {return i/2;} 
	
	/**
	 * This method allows us to look at the left child of the index we are looking at
	 * @param i is the index we are looking at
	 * @return is the left child location
	 */
	public int left(int i) {return 2*i;} 
	
	/**
	 * This method allows us to look at the right child of the index we are looking at
	 * @param i is the index we are looking at
	 * @return is the right child location
	 */
	public int right(int i) {return 2*i + 1;}
	
	/**
	 * This method allows us to look at the patient with the highest priority
	 * @return the returns the name of the patient with the highest priority
	 */
	public String peek() {
		if(isEmpty()) 
			throw new NullPointerException();
		String patientName = heap[1].name;
		return patientName;
	}
	
	/**
	 * This method allows us to look at the patient with the highest priority
	 * @return the returns the priority of the patient with the highest priority
	 */
	public int peekPriority() {
		if(isEmpty())
			throw new NullPointerException();
		int patientPriority = heap[1].priority;
		return patientPriority;
	}
	
	/**
	 * This method allows us to to change the priority of one of the patients in the queue
	 * it will then bubble up or down depending on what is needed
	 * @param name is the name of the patient we are changing
	 * @param newPriority is the new priority they will be at
	 */
	public void changePriority(String name, int newPriority) {
		for(int i=1; i<size+1;i++) {
			// go through the array and if we find a name then change it
			if(heap[i].name.equals(name)) {
				int oldNum = heap[i].priority;
				Patient newPatient = new Patient(heap[i].name, newPriority);
				heap[i] = newPatient;
				// check if we need to bubble up or down
				if(oldNum < newPriority) {
					bubbleDown(i);
					break;
				}
				else {
					bubbleUp(i);
					break;
				}
				
			}
		}
	}
	
	/**
	 * This method tells us if the queue is empty or not
	 * @return true or false if it is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * This method return the size of the queue
	 * @return the size of the queue
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This method clears the queue
	 */
	public void clear() {
		heap = new Patient[10];
		heap[0] = null;
		size = 0;
	}
	
	/**
	 * This method tells java how to print out the queue so we can look at it correctly
	 */
	public String toString() {
		String s = "{";
		for (int i = 1; i <= size; i++)
		s += (i == 1 ? "" : ", ") + heap[i];
		return s + "}";
	}
}
