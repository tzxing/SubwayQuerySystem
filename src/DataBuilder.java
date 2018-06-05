import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DataBuilder {  
      
    public static List<Station> line1 = new ArrayList<Station>();//1����  
    public static List<Station> line2 = new ArrayList<Station>();//2����  
    public static List<Station> line3 = new ArrayList<Station>();//3����  
    public static List<Station> line4 = new ArrayList<Station>();//4����  
    public static List<Station> line10 = new ArrayList<Station>();//10����  
    public static List<Station> lineS1 = new ArrayList<Station>();//S1����  
    public static List<Station> lineS3 = new ArrayList<Station>();//S3����  
    public static List<Station> lineS7 = new ArrayList<Station>();//S7����  
    public static List<Station> lineS8 = new ArrayList<Station>();//S8����  
    public static List<Station> lineS9= new ArrayList<Station>();//S9����  
    public static Set<List<Station>> lineSet = new HashSet<List<Station>>();//�����߼��� 
    static {          
        //1����  
        String line1Str = "������վ����ɽ����԰վ���Ͼ�վ����ģ����·վ��������վ����¥վ���齭·վ���½ֿ�վ���Ÿ�԰վ����ɽ��վ���л���վ��������վ����¡��վ��������վ��������վ���Ͼ���վ��˫�����վ���Ӷ���վ��ʤ̫·վ���ټҺ�վ��С����վ����ɽ·վ����ӡ���վ�����ߴ��վ����ҽ�󡤽��վ�óѧԺվ���Ͼ���Ժվ���й�ҩ�ƴ�ѧվ";  
        String[] line1Arr = line1Str.split("��");  
        for(String s : line1Arr)
        {  
            line1.add(new Station(s,"1����"));  
        }  
        for(int i =0;i<line1.size();i++)
        {  
        	if(i<line1.size()-1)
        		line1.get(i).edgeList.add(line1.get(i+1).label);
        	if(i>0)
        		line1.get(i).edgeList.add(line1.get(i-1).label);
        }  
          
        /*******************************************************************************/  
        //2����  
        String line2Str = "�ͷ���վ��������վ��Ԫͨվ�����嶫վ����¡���վ�������Ŵ��վ���ƽ�·վ��Ī���վ��������վ���Ϻ�·վ���½ֿ�վ�����й�վ��������վ�����ʹ�վ����ޣ԰վ������վ��Т����վ�������վ����Ⱥվ������·վ���ɺ���վ��ѧ��·վ����������վ����ɽ��԰վ���ϴ�����У��վ������·վ";  
        String[] line2Arr = line2Str.split("��");  
        for(String s : line2Arr)
        {  
            line2.add(new Station(s,"2����"));  
        }  
        for(int i =0;i<line2.size();i++)
        {  
        	if(i<line2.size()-1)
        		line2.get(i).edgeList.add(line2.get(i+1).label);
        	if(i>0)
        		line2.get(i).edgeList.add(line2.get(i-1).label);
        }  
          
        /*******************************************************************************/  
        //3����  
        String line3Str = "�ֳ�վ���ǻ�·վ���������ѧԺվ��̩��·վ�������վ�����޶�·վ����Ԫ��վ�������㳡վ��С��վ���Ͼ�վ���Ͼ���ҵ��ѧ����ׯվ��������վ������վ�����й�վ��������վ��������վ���䶨��վ���껨��վ��������վ������·վ�������㳡վ���Ͼ���վ�����˴��վ��ʤ̫��·վ����Ԫ��·վ��������վ�����Ŵ��վ�����������У��վ�����ܶ�·վ";  
        String[] line3Arr = line3Str.split("��");  
        for(String s : line3Arr)
        {  
            line3.add(new Station(s,"3����"));  
        }  
        for(int i =0;i<line3.size();i++)
        {  
        	if(i<line3.size()-1)
        		line3.get(i).edgeList.add(line3.get(i+1).label);
        	if(i>0)
        		line3.get(i).edgeList.add(line3.get(i-1).label);
        }  
          
        //4����
        String line4Str = "����վ�����ա���ʦ���ݳ���վ������·վ����¥վ��������վ���Ż�ɽվ�����Ӵ�վ��������վ��������վ���۱�ɽվ�������ܲ�����ׯվ������·վ����ͨ·վ����ɽվ������վ���ϱ�վ����������վ�����ֺ�վ";  
        String[] line4Arr = line4Str.split("��");  
        for(String s : line4Arr)
        {  
            line4.add(new Station(s,"4����"));  
        }  
        for(int i =0;i<line4.size();i++)
        {  
        	if(i<line4.size()-1)
        		line4.get(i).edgeList.add(line4.get(i+1).label);
        	if(i>0)
        		line4.get(i).edgeList.add(line4.get(i-1).label);
        }  
        
        
        
        /*******************************************************************************/         
        //10����  
        String line10Str = "��ɽ·վ���ĵ�·վ������·վ���Ͼ���ҵ��ѧվ���ֿ�����վ���ٽ�վ��������վ���̲�԰վ���ζ����վ����������վ��Ԫͨվ����ʤվ��С��վ��������վ";  
        String[] line10Arr = line10Str.split("��");  
        for(String s : line10Arr)
        {  
            line10.add(new Station(s,"10����"));  
        }  
        for(int i =0;i<line10.size();i++)
        {  
        	if(i<line10.size()-1)
        		line10.get(i).edgeList.add(line10.get(i+1).label);
        	if(i>0)
        		line10.get(i).edgeList.add(line10.get(i-1).label);
        }  
          
        /*******************************************************************************/         
        //s1����  
        String lineS1Str = "�Ͼ���վ������ɽվ���Ӻ���ѧ�������·վ����ӡ���վ��������·վ������·��վ������·��վ��»�ڻ���վ���ո��³ǽ���վ";  
        String[] lineS1Arr = lineS1Str.split("��");  
        for(String s : lineS1Arr)
        {  
            lineS1.add(new Station(s,"S1����"));  
        }  
        for(int i =0;i<lineS1.size();i++)
        {  
        	if(i<lineS1.size()-1)
        		lineS1.get(i).edgeList.add(lineS1.get(i+1).label);
        	if(i>0)
        		lineS1.get(i).edgeList.add(lineS1.get(i-1).label);
        }  
        
      //s3����  
        String lineS3Str = "�Ͼ���վ��������԰վ��������վ������·վ������վ���ͷ���վ������·վ��ƽ�����վ������վ������·վ���챣վ������վ��������վ��������վ��˫��վ��ʯ�Ӻ�վ�������³�վ����ɽվ���߼ҳ�վ";  
        String[] lineS3Arr = lineS3Str.split("��");  
        for(String s : lineS3Arr)
        {  
            lineS3.add(new Station(s,"S3����"));  
        }  
        for(int i =0;i<lineS3.size();i++)
        {  
        	if(i<lineS3.size()-1)
        		lineS3.get(i).edgeList.add(lineS3.get(i+1).label);
        	if(i>0)
        		lineS3.get(i).edgeList.add(lineS3.get(i-1).label);
        }  
        
        
      //s7����  
        String lineS7Str = "�ո��³ǽ���վ������վ���ո��³���ˮվ��Ⱥ��վ��������վ����ˮվ����ɽ��վ����ׯվ������ɽվ";  
        String[] lineS7Arr = lineS7Str.split("��");  
        for(String s : lineS7Arr)
        {  
            lineS7.add(new Station(s,"S7����"));  
        }  
        for(int i =0;i<lineS7.size();i++)
        {  
        	if(i<lineS7.size()-1)
        		lineS7.get(i).edgeList.add(lineS7.get(i+1).label);
        	if(i>0)
        		lineS7.get(i).edgeList.add(lineS7.get(i-1).label);
        }  
        
        
        
          
        /*******************************************************************************/         
        //s8����  
        String lineS8Str = "̩ɽ�´�վ��̩��·վ�����¿�����վ����Ϣ���̴�ѧվ��ж�׵�վ����վ������վ����«վ������԰վ�����Ͽ�����վ������վ������վ�����ɽ��԰վ�����ݹ㳡վ������վ���˰���վ����ţ��վ";  
        String[] lineS8Arr = lineS8Str.split("��"); 
        for(String s : lineS8Arr)
        {  
            lineS8.add(new Station(s,"S8����"));  
        }  
        for(int i =0;i<lineS8.size();i++)
        {  
        	if(i<lineS8.size()-1)
        		lineS8.get(i).edgeList.add(lineS8.get(i+1).label);
        	if(i>0)
        		lineS8.get(i).edgeList.add(lineS8.get(i-1).label);
        }  
        
      //s9����  
        String lineS9Str = "����·��վ��ͭɽվ��ʯ��վ������վ���Ž���վ���ߴ�վ";  
        String[] lineS9Arr = lineS9Str.split("��");  
        for(String s : lineS9Arr)
        {  
            lineS1.add(new Station(s,"S9����"));  
        }  
        for(int i =0;i<lineS9.size();i++)
        {  
        	if(i<lineS9.size()-1)
        		lineS9.get(i).edgeList.add(lineS9.get(i+1).label);
        	if(i>0)
        		lineS9.get(i).edgeList.add(lineS9.get(i-1).label);
        }  
          
        lineSet.add(line1);  
        lineSet.add(line2);  
        lineSet.add(line3);  
        lineSet.add(line4);
        lineSet.add(line10);  
        lineSet.add(lineS1);  
        lineSet.add(lineS3);
        lineSet.add(lineS7);
        lineSet.add(lineS8);
        lineSet.add(lineS9);
        
    }  
    public static void main(String[] args)
	{
		DataBuilder dataBuilder=new DataBuilder();
		dataBuilder.toString();
		System.out.println(DataBuilder.line1);
		LinkedList<Integer> linkedList=new LinkedList<>();
		linkedList.add(1);
		System.out.println(linkedList);
	}
}  