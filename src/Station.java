import java.util.LinkedList;

public class Station
{
	public String label;
	public String line;
	public LinkedList<String> edgeList;
	public int dist;
	public String preStation;
	public Station()
	{
		edgeList=new LinkedList<>();
	}
	public Station(String label,String line,String edge)
	{
		this();
		this.label=label;
		this.line=line;
		this.edgeList.add(edge);
	}
	public Station(String label,String line)
	{
		this();
		this.label=label;
		this.line=line;
	}
	public String toString()
	{
		return this.label;
	}
	public String Line()
	{
		return this.line;
	}
}
