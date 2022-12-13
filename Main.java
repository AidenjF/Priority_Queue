
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatientQueue queue = new PatientQueue();
		queue.enqueue("Elodie", 3);
		queue.enqueue("Isa", 7);
		queue.enqueue("Sadie", 1);
		queue.enqueue("Ava", 9);
		System.out.println(queue);
		System.out.println("Size should be 4: " + queue.size());
		System.out.println("Should be false: " + queue.isEmpty());
		System.out.println("Should be Sadie 1: " + queue.peek() + " " + queue.peekPriority());
		queue.changePriority("Sadie", 4);
		System.out.println(queue);
		queue.enqueue("Truman", 1);
		queue.enqueue("Wheatley", 2);
		queue.enqueue("Katie", 5);
		queue.enqueue("Emily", 6);
		queue.enqueue("Cara", 8);
		queue.enqueue("Elodie", 8);
		System.out.println(queue);
		System.out.println("Should be Truman: " + queue.dequeue());
		System.out.println(queue);
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		System.out.println("Should be Katie 5: " + queue.peek() + " " + queue.peekPriority());
		queue.changePriority("Katie", 9);
		System.out.println("Katie's priority should now be 9: " + queue);
		queue.changePriority("Ava", 1);
		System.out.println("Ava's priority should now be 1: " + queue);
		queue.clear();
		System.out.println("Should be true: " + queue.isEmpty());
		System.out.println("Should throw an error");
		queue.peek();
	}

}
