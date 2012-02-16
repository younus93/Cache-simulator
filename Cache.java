
public class Cache {
	public int offset_length;
	public int NumofSets_length;
	public int tag_length;
	CacheLine[] cache;

	public Cache(int L, int N, int K){
		// Generate the number of bits for the offset of the address
		// This is used to find out how many bits the input address should be shifted
		this.offset_length = (int) Math.ceil(Math.sqrt(L)); // offset
		
		// How many bits in the address should be used to pick what set is used
		
		this.NumofSets_length = (int) Math.ceil(Math.sqrt(N)); // set #
		
		if(N==2){
			// sqrt fix
			this.NumofSets_length =1;
		}
		
		// use the rest of the address to generate the actual tag.
		this.tag_length = (16 - NumofSets_length) - offset_length; // tag_length
		
		// create the required number of Cache Lines
		this.cache = new CacheLine[this.NumofSets_length];
		
		// ensure that the CacheLines are instantiated 
		for(int i=0; i<cache.length;i++){
			cache[i] = new CacheLine(K,N);
		}
		
		System.out.println("Offset: " +this.offset_length + " bits.\nSets: " + this.NumofSets_length + " bits.\nTagL: " + this.tag_length + " bits\n");
	}
	
	public int calcSet(String input){
		// convert from a hex string to int
		int result = Integer.parseInt(input, 16);
		// shift the bits to remove the offset and then mask it so that there's only the bits for the sets..
		result = ((result >> this.offset_length	) & ((int) Math.pow(2, this.NumofSets_length)-1));
		return result;
	}
	public int genTag(String input){
		// convert from a hex string to int
		int result = Integer.parseInt(input, 16);
		// shift the integer over so that it's only the bits of the tag left
		result = (result >> (this.NumofSets_length + this.offset_length));
		return result;
	}
	public void add(String input){
		int set = this.calcSet(input);
		int tag = this.genTag(input);
		// leave the implimentation of LRU up to the cacheLine
		cache[set].add(Integer.toString(tag));
	}
	
}
