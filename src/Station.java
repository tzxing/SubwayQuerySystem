import java.util.LinkedList;

public class Station implements Cloneable
{
	public String label;
	public LinkedList<String> line;
	public LinkedList<String> edgeList;
	public int dist;
	public String preStation;
	public boolean isTransfer;

	public Station()
	{
		isTransfer = false;
		edgeList = new LinkedList<>();
		line = new LinkedList<>();
	}

	public Station(String label, String line, String edge)
	{
		this();
		this.label = label;
		this.line.add(line);
		this.edgeList.add(edge);
	}

	public Station(String label, String line)
	{
		this();
		this.label = label;
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
		boolean ret = false;
		for (String a : this.line)
		{
			for (String b : station.line)
				if (a.equals(b))
					ret = true;
		}
		return ret;
	}

	@Override
	public Station clone() throws CloneNotSupportedException
	{
		Station sc = null;
		try
		{
			sc = (Station) super.clone();
			sc.label = new String(this.label);
			sc.line = new LinkedList<>(this.line);
			sc.edgeList = new LinkedList<>(this.edgeList);
			sc.preStation = new String(this.preStation);
		}
		catch (Exception e)
		{

		}
		return sc;
	}
}
