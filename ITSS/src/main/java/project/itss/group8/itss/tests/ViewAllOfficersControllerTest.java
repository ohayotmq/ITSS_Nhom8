//package project.itss.group8.itss.tests;
//
//import project.itss.group8.itss.controller.ViewAllOfficersController;
//
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//
//class ViewAllOfficersControllerTest {
//    ViewAllOfficersController controller = new ViewAllOfficersController();
//
//    //Valid Month
//    @Test
//    void test1() {assertTrue(controller.checkMonthInput("1"));}
//
//    //Valid Month Boundary
//    @Test
//    void test2() {assertTrue(controller.checkMonthInput("12"));}
//
//    //Invalid Month Boundary
//    @Test
//    void test3() {assertFalse(controller.checkMonthInput("0"));}
//
//    //Valid String
//    @Test
//    void test4() {assertTrue(controller.checkMonthInput("04"));}
//
//    //Invalid String
//    @Test
//    void test5() {assertFalse(controller.checkMonthInput("Abc123"));}
//
//    //Invalid String
//    @Test
//    void test6() {assertFalse(controller.checkMonthInput("Hi"));}
//
//    //Invalid String
//    @Test
//    void test7() {assertFalse(controller.checkMonthInput(".;',"));}
//}