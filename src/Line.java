import java.util.LinkedList;

public class Line
{
	String label;
	LinkedList<String> edge;
	public Line()
	{
		edge=new LinkedList<>();
	}
	public Line(String label)
	{
		this();
		this.label=label;
	}

}
