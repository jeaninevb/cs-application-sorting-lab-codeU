/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
		
// 		for(T elt: list){			
// 			System.out.print(elt + "   ");
// 		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        
        List<T> left = new ArrayList<T>();
		List<T> right = new ArrayList<T>();
		int center;
				
		if(list.size() == 1){
			return list;
		}
		else{
		
			center = (list.size())/2;
			for(int i = 0; i < center; i++){
			
				left.add(list.get(i));
			}
			for(int j = center; j < list.size(); j++){
			
				right.add(list.get(j));
			}
			
			left = mergeSort(left, comparator);
			right = mergeSort(right, comparator);
		}
		
        merge(left, right, list, comparator);
        
        List<T> sortedList = new ArrayList<T>();
        
        for(T elt: list){
        	sortedList.add(elt);
        }
        
        return sortedList;
	}
	
	
	public void merge(List<T> left, List<T> right, List<T> list, Comparator<T> comparator){
	
		int leftIndex = 0;
		int rightIndex = 0;
		int listIndex = 0;
		
		while(leftIndex < left.size() && rightIndex < right.size()){
		
			if( comparator.compare(left.get(leftIndex), right.get(rightIndex)) < 0 ){
				
				list.set(listIndex, left.get(leftIndex));
				leftIndex++;
			}
			else{
				list.set(listIndex, right.get(rightIndex));
				rightIndex++;			
			}
			listIndex++;
		}
		
		List<T> rest;
		int restIndex;
		if(leftIndex >= left.size()){
		
			rest = right;
			restIndex = rightIndex;
		}
		else{
		
			rest = left;
			restIndex = leftIndex;
		}
		
		for(int i = restIndex; i < rest.size(); i++){
		
			list.set(listIndex, rest.get(i));
			listIndex++;
		}
		
		
// 		for(int i = 0; i < list.size(); i++){
// 			
// 			System.out.print(list.get(i) + "   ");
// 		}
	} 
	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
		
		PriorityQueue<T> queue = new PriorityQueue<T>(list.size(), comparator);
		List<T> sortedList = new ArrayList<T>();
		
		for(T elt: list){
			queue.offer(elt);
		}
		
		while(queue.peek() != null){
			
			sortedList.add(queue.peek());
			queue.poll();
		}
		
		list.clear();
		for(T elt: sortedList){
			list.add(elt);
		}
	}	
	

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
	
		PriorityQueue<T> queue = new PriorityQueue<T>(k, comparator);
		
		for(T elt: list){
			
			queue.offer(elt);
			if(queue.size() > k){
				
				queue.poll();
			}
		}
		
		list.clear();
		
		while(queue.peek() != null){
		
			list.add(queue.peek());
			queue.poll();
		}
		
        return list;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
