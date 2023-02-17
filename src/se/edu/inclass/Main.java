package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {

	private TaskNameComparator taskNameComparator;

	public static void main(String[] args) {
		System.out.println("Welcome to Task (stream) manager\n");
		DataManager dm = new DataManager("./data/data.txt"); // data file
		ArrayList<Task> tasksData = dm.loadData();//passing the file and put data in array list


		System.out.println();
		System.out.println("Printing deadlines before soring");
		printDeadlines(tasksData);

		System.out.println("Total number of deadlines after sorting: " + countDeadlines(tasksData));
		//System.out.println("Total number of deadlines using stream: " + countDeadlinesUsingStream(tasksData));
		//printData(tasksData);
		printDataUsingStreams(tasksData);
		ArrayList<Task> filteredList = filterTaskListUsingStream("11",tasksData);


	}

	private static int countDeadlines(ArrayList<Task> tasksData) {
		int count = 0;
		for (Task t : tasksData) {
			if (t instanceof Deadline) {
				count++;
			}
		}
		return count;
	}

	private static int countDeadlinesUsingStream(ArrayList<Task> tasks){
		int count = (int)tasks.stream() //instanceof Deadline - this is the checking point
				.filter(t -> t instanceof Deadline)
				.count();
		return count;
	}
	public static void printData(ArrayList<Task> tasksData) {
		System.out.println("Printing data using iteration");
		for (Task t : tasksData) {
			System.out.println(t);
		}
		//System.out.println(); sout
	}

	public static void printDataUsingStreams(ArrayList<Task> tasks) {
		System.out.println("Printing data using stream");
		tasks.stream()// convert in stream
				.forEach(System.out::println); //referring the method and refer all the methods in the stream

	}

	public static void printDeadlines(ArrayList<Task> tasksData) {
		System.out.println("print ddl using it.");
		for (Task t : tasksData) {
			if (t instanceof Deadline) {
				System.out.println(t);
			}
		}
	}

	public static void printDeadlineUsingStream(ArrayList<Task> tasks) {
		System.out.println("Print DDL using stream");
		tasks.stream() //instanceof Deadline - this is the checking point
				.filter(t -> t instanceof Deadline) //predicate - boolean output //lamda function
				.sorted((a,b) -> a.getDescription().compareToIgnoreCase((b.getDescription()))); //compare the first one with 2nd one

	}

	public static ArrayList<Task> filterTaskListUsingStream(String filterString,ArrayList<Task> tasks) {
		ArrayList<Task> filteredList = new ArrayList<>();
		tasks.stream()
				.filter(t->t.getDescription().contains(filterString))
				.collect(toList());
		return filteredList;
	}
}


