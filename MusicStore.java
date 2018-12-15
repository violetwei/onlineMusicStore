package assignment4;

import java.util.ArrayList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
	
	// create three hashMaps fields
    private MyHashTable<String,Song> titleTable = new MyHashTable<String,Song>(100); //song table
    private MyHashTable<String,ArrayList<Song>> artistTable = new MyHashTable<String,ArrayList<Song>>(100);
    private MyHashTable<Integer,ArrayList<Song>> yearTable = new MyHashTable<Integer,ArrayList<Song>>(100);
    //ADD YOUR CODE ABOVE HERE
    
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
    	
    	//initialize attributes
    	this.titleTable =  new MyHashTable<String,Song>(100);
    	this.artistTable =  new MyHashTable<String,ArrayList<Song>>(100);
    	this.yearTable = new MyHashTable<Integer,ArrayList<Song>>(100);
    	
    	
        // populate the tile table
    	for(Song song: songs) {
    		
    		this.titleTable.put(song.getTitle(), song);
    	}
    	
    	
    	// populate the artist table
    	//for each song in the input list
    	for(Song song: songs) {
    		// get the clone and the artist
    		String artist = song.getArtist();
    		
    		// if empty: populate
    		if(this.artistTable.get(artist) == null) {
    			ArrayList<Song> songArray = new ArrayList<Song>();
    			songArray.add(song); //add the first song
    			this.artistTable.put(artist, songArray); // put into the HashTable
    			
    		}
    		else { //else: artist entry has something
    			//append the new song to the arrayList of songs for that artist
    			this.artistTable.get(artist).add(song);    			
    		}
    	}
    	
    	
    	//populate the yearTable
    	//for each song in the input list
    	for(Song song: songs) {
    		//get the clone and the year
    		int year = song.getYear();
    		
    		if(this.yearTable.get(year) == null) {
    			ArrayList<Song> songArray = new ArrayList<Song>();
    			songArray.add(song);
    			this.yearTable.put(year, songArray);
    		}
    		else {
    			this.yearTable.get(year).add(song);
    		}
    	}
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
    	
    	//add to titleTable
    	if(this.titleTable.get(s.getTitle()) == null) {
    		this.titleTable.put(s.getTitle(), s);
    	}
    	
    	//add to artistTable
    	
    	// if empty: populate
		if(this.artistTable.get(s.getArtist()) == null) {
			ArrayList<Song> songArray = new ArrayList<Song>();
			songArray.add(s); //add the first song
			this.artistTable.put(s.getArtist(),  songArray); // put into the HashTable
			
		}
		else { //else: artist entry has something
			//append the new song to the arrayList of songs for that artist
			this.artistTable.get(s.getArtist()).add(s);    			
		}
    	
		
		//add to yearTable
		if(this.yearTable.get(s.getYear()) == null) {
			ArrayList<Song> songArray = new ArrayList<Song>();
			songArray.add(s);
			this.yearTable.put(s.getYear(), songArray);
		}
		else {
			this.yearTable.get(s.getYear()).add(s);
		}

		
        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
    	
    	Song found = titleTable.get(title);
    	
        return found; //remove
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
    	ArrayList<Song> found = artistTable.get(artist);
    	
        return found;//remove
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
    	ArrayList<Song> found = yearTable.get(year);
    	
        return found;//remove
        //ADD CODE ABOVE HERE
        
    }
}
