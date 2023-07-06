import org.junit.Assert;
import org.junit.Test;

import project.itss.group11.itss.service.IEmployeeTimekeepingOverview;

import java.time.LocalDateTime;

public class EmployeeTimekeepingOverviewTest {
    IEmployeeTimekeepingOverview employeeTimekeepingOverview = new project.itss.group11.itss.service.Impl.EmployeeTimekeepingOverviewImpl();

    LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 8, 00, 0);
    LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 17, 30, 0);
    // Kiểm thử xem người đó có đúng về sớm không
    @Test
    public void test1() {
        LocalDateTime returnTime = LocalDateTime.of(2023, 1, 1, 14, 30, 0);
        String result = employeeTimekeepingOverview.getReturnEarlyTime(returnTime,endTime);
        Assert.assertEquals("Yes",result);
    }
    // Kiếm thử xem có đúng người nay không về sớm đúng không
    @Test
    public void test2() {
        LocalDateTime returnTime = LocalDateTime.of(2023, 1, 1, 18, 30, 0);
        String result = employeeTimekeepingOverview.getReturnEarlyTime(returnTime,endTime);
        Assert.assertEquals("No",result);
    }
    // Kiểm thử xem người đó có đến muộn không
    @Test
    public void test3() {
        LocalDateTime comeTime = LocalDateTime.of(2023, 1, 1, 9, 30, 0);
        String result = employeeTimekeepingOverview.getComeLateTime(comeTime,startTime);
        Assert.assertEquals("Yes",result);
    }
    // Kiếm thử xem người này có đến đúng giờ không
    @Test
    public void test4() {
        LocalDateTime comeTime = LocalDateTime.of(2023, 1, 1, 7, 30, 0);
        String result = employeeTimekeepingOverview.getComeLateTime(comeTime,startTime);
        Assert.assertEquals("No",result);
    }


}
