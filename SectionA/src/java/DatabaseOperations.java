
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pagnarith
 */
public class DatabaseOperations {
    Connection con = null;
    public Connection getCon() throws ClassNotFoundException, SQLException
    {
        if(con==null)
        {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
        }
        return con;
    }
    
    public boolean insertRecord(Student s)
    {
        try
        {
            getCon();
        PreparedStatement ps = con.prepareStatement("insert into student values(?, ?);");
        ps.setInt(1, s.getId());
        ps.setString(2, s.getFirstName());     
        ps.execute();
        return true;
        }
        catch(SQLException | ClassNotFoundException e)
        {
        return false;
        }
    }
    
    public int insertRecords(List<Student> studentList)
    {
        int count = 0;
        try{
                    getCon();
                    PreparedStatement ps = con.prepareStatement("insert into student values(?, ?);");
                    for(Student student: studentList)
                    {
                        ps.setInt(1, student.getId());
                        ps.setString(2, student.getFirstName());
                        ps.execute();
                        count++;
                    }
                    return count;
                }
        catch(SQLException | ClassNotFoundException e)
        {
            return count;
        }
    }
    
    public Student getSingleRecord(int sno)
    {
        Student s = new Student();
        try{
        getCon();
        PreparedStatement ps = con.prepareStatement("select * from student where sno = ?;");
        ps.setInt(1, sno);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int snoTemp = rs.getInt(1);
        String firstNameTemp = rs.getString(2);
        s.setId(snoTemp);
        s.setFirstName(firstNameTemp);
        return s;
        }
        catch(SQLException | ClassNotFoundException e)
        {
          return s;
        }
    }
    
    public ArrayList<Student> getAllRecords()
    {
        ArrayList<Student> students = new ArrayList<Student>();
        try{
            getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student;");
            while(rs.next())
            {
                Student s = new Student();
                s.setFirstName(rs.getString(2));
                s.setId(rs.getInt(1));
                students.add(s);
            }
            return students;
        }
        catch(SQLException | ClassNotFoundException e)
        {
            return students;
        }
    }
    
    public boolean deleteRecord(Student s)
    {
        try
        {
        getCon();
        PreparedStatement ps = con.prepareStatement("delete from student where sno=?;");
        ps.setInt(1, s.getId());
        ps.executeUpdate();
        return true;
        }
        catch(SQLException | ClassNotFoundException e)
        {
        return false;
        }
    }

}
