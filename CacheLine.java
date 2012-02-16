
public class CacheLine {
	public String[] tags;
	public String[][] words;
	int oldest;
	int position;

	
	public CacheLine(int K, int N){
		int tags_width = K;
		tags = new String[tags_width];
		words = new String[K][4];
		this.oldest =0;
		this.position = 0;
	}
	public boolean hasTag(String input){
		// loop through the tags and check if the given tag exists
		for(int i=0; i<tags.length; i++){
			if(input==tags[i]){
				return true;
				
			}
		}
		return false;
	}
	public void add(String input){
		if(oldest>tags.length)
		{
			oldest=0;
			
		}
		if(this.isFull())
		{
			this.tags[oldest] = input;
			oldest++;

		}
		else
		{
			this.tags[position]=input;
		}
		position++;
	}
	public boolean isFull(){
		boolean result=true;
		for(int i =0; i<this.tags.length;i++){
			if(this.tags[i]==null){
				result=false;
				break;
			}
		}
		return result;
	}
	
}
