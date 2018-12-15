package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;



public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
    
        this.numBuckets=initialCapacity;
        this.numEntries = 0;
        this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(initialCapacity);
    	
        for(int i = 0; i < initialCapacity; i++) {
    			this.buckets.add(new LinkedList<HashPair<K,V>>());
        }
    	
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
		
        //  ADD YOUR CODE BELOW HERE
    	if(key == null){
			throw new IllegalArgumentException();
		}
    		
    		if(this.buckets.get(hashFunction(key)).isEmpty()) 
    		{
    			this.buckets.get(hashFunction(key)).add(new HashPair<K,V>(key, value));
    			this.numEntries++;
    			if(((double)this.numEntries)/this.numBuckets > MAX_LOAD_FACTOR) 
    			{
        			rehash();							
        		}
    			return null;
    		}else 
    		{
    			for(HashPair<K,V> item: this.buckets.get(hashFunction(key))) 
    			{
            		if((item.getKey()).equals(key)) 
            		{
            			V oldValue=item.getValue();
            			item.setValue(value);     			
            			return oldValue;			
            		}              	
            	}
    			(this.buckets.get(this.hashFunction(key))).add(new HashPair<K,V>(key, value));
            	this.numEntries++;
            	if(((double)this.numEntries)/this.numBuckets > MAX_LOAD_FACTOR) {
        			rehash();							
        		}
            	return null; 
    			
    		}
    	
    		//  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
    		if(key == null){
    			throw new IllegalArgumentException();
    		}
    		
    		if (this.buckets.get(hashFunction(key)).isEmpty()) return null;
    		
    		for(HashPair<K,V> item: this.buckets.get(hashFunction(key))) 
    		{
        		if((item.getKey()).equals(key)) 
        		{
        			return item.getValue();			// return value of the item
        		}
        	}
         return null;//remove
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
    	if(key == null){
			throw new IllegalArgumentException();
		}
    		if (this.buckets.get(hashFunction(key)).isEmpty()) {
    			return null;
    		}
    		int i=0; //index
    		for(HashPair<K,V> item: this.buckets.get(hashFunction(key))) 
    		{
        		if((item.getKey()).equals(key)) 
        		{
        			V oldValue=item.getValue();
        			this.buckets.get(this.hashFunction(key)).remove(i);
        			return oldValue;		
        		}
        		i++;
        	}  	
        return null;//remove
        //ADD YOUR CODE ABOVE HERE
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
    
    		MyHashTable<K, V> table=new MyHashTable<K, V>(this.numBuckets*2);   		
    		for(LinkedList<HashPair<K,V>> list: this.buckets) 
    		{
    			for(HashPair<K,V> item: list) 
    			{
    				table.put(item.getKey(), item.getValue());
            	}	
        	}
    		this.buckets=table.buckets;
    		this.numBuckets=table.numBuckets;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
    	
    		if (this.numEntries==0) {
    			ArrayList<K> keys=new ArrayList<K>();
    			keys=null;
    			return keys;
    		}
    		
    		ArrayList<K> keys=new ArrayList<K>(this.numEntries);  
    		
    		for(LinkedList<HashPair<K,V>> list: this.buckets) {
			for(HashPair<K,V> item: list) 
			{
				keys.add(item.getKey());
        		}	
    		}   	
        return keys;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
    		MyHashTable<V, K> hashTable=new MyHashTable<V, K>(this.numBuckets);
    		for(LinkedList<HashPair<K,V>> list: this.buckets) 
    		{
    			for(HashPair<K,V> item: list) 
    			{
    				hashTable.put(item.getValue(), item.getKey());
            	}	
        	}
    		
    		ArrayList<V> values=new ArrayList<V>(hashTable.numEntries);
    		for(LinkedList<HashPair<V,K>> list: hashTable.buckets) 
    		{
    			for(HashPair<V,K> item: list) 
    			{
    				values.add(item.getKey());
            	}	
        	}
    		return values;
        //ADD CODE ABOVE HERE
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;
        
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
        	entries = new LinkedList<HashPair<K,V>>();
        	for(LinkedList<HashPair<K,V>> list: MyHashTable.this.getBuckets()) 
        	{
        		for(HashPair<K,V> item: list) {
        			entries.add(item);
        		}
        	}
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
            return (!entries.isEmpty());
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
        	return entries.removeFirst();
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}
