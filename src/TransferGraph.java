import java.util.HashMap;
import java.util.Map;

public class TransferGraph
{
	public int[][] matrix;
	public Map<String,Integer> lines;
//	public TransferGraph()
//	{
//		matrix=new int[10][10];
//		lines=new HashMap<>();
//	}
	public TransferGraph(Map<String, Station> allStation,int n)
	{
		matrix=new int[n][n];
		lines=new HashMap<>();
		int index=0;
		for(Station station:allStation.values())
		{
			for(String string:station.line)
			{
				if(lines.get(string)==null)
				{
					lines.put(string, index);
					index++;
				}
			}

			if(station.isTransfer)
			{
				for(String string1:station.line)
				{
					for(String string2:station.line)
					{
						matrix[lines.get(string1)][lines.get(string2)]=1;
					}
				}
			}
		}
		for(int i=0;i<matrix.length;i++)
			matrix[i][i]=-1;
	}

	public String toString()
	{
		StringBuffer ret=new StringBuffer();
		Object linesname[];
		ret.append("     ");
		for(String s1:lines.keySet())
			ret.append("   "+s1);
		ret.append("\n");
		linesname= lines.keySet().toArray();
		for(String s1:lines.keySet())
		{
			ret.append(s1+"            ");
			for(String s2:lines.keySet())
				ret.append("    "+matrix[lines.get(s1)][lines.get(s2)]);
			ret.append("\n");
		}
		return ret.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args)
	{
		SubwayGraph subwayGraph=new SubwayGraph(DataBuilder.lineSet);
		TransferGraph transferGraph=new TransferGraph(subwayGraph.vertices,10);
		System.out.println(transferGraph.toString());
		
	}
	
	
	
	
	
	
}
