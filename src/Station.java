import java.util.LinkedList;

public class Station
{
	public String label;
	public LinkedList<String> line;
	public LinkedList<String> edgeList;
	public int dist;
	public String preStation;
	public boolean isTransfer;
	public Station()
	{
		isTransfer=false;
		edgeList=new LinkedList<>();
		line=new LinkedList<>();
	}
	public Station(String label,String line,String edge)
	{
		this();
		this.label=label;
		this.line.add(line);
		this.edgeList.add(edge);
	}
	public Station(String label,String line)
	{
		this();
		this.label=label;
		this.line.add(line);
	}
	public String toString()
	{
		return this.label;
	}
	public String Line()
	{
		return this.line.toString();
	}
	public boolean onTheSameLine(Station station)
	{
		boolean ret=false;
		for(String a:this.line)
		{
			for(String b:station.line)
				if(a.equals(b))
					ret=true;
		}
		return ret;
	}
}
