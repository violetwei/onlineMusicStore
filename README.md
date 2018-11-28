# onlineMusicStore
simulate an online Music place in Java with HashTable

The class MyHashTable has the following fields:

• An int for the number of entries stored inside the table.

• An int for the number of buckets the table has (Note that this value is initialized by the constructor, but could change later on if the number of entries increases).

• A final double representing the load factor for the hash table.

• An ArrayList of buckets used to store the entries to the table. Where each bucket is a
LinkedList of HashPairs.


Implement the following public methods in the MyHashTable class:


• The constructor MyHashTable() which takes an int as input representing the initial capacity of the table.1 Using the input, the constructor initializes all the fields.


• A put() method that takes a key and a value as input. The method adds a HashPair of the key and value to the hash table. If a HashPair with the key already exists in the hash table, then you should overwrite the old value associated with the key with the new one. This method should be O(1). If in this hash table there was a previous value associated to the given key, then the method overwrites it with the new value and returns the old one. If there was no value associated to the given key, than the method returns null.


• A get() method which takes a key as input and returns the value associated with it. If there is no such key in the hash table, then the method should return null. This method should run in O(1) on average.


• A remove() method that takes a key as input and removes from the table the entry (i.e. the HashPair) associated to this key. The method should return the value associated to the key. If the key is not found, then the method returns null. This method should run in O(1) on average.


• A rehash() method which takes no input and modifies the table so that it contains double the number of buckets. This method should be O(n) where n the number of entries in the table.
