package com.school.work.controllers;

import com.school.work.models.Student;
import com.school.work.dao.StudentDao;
import com.school.work.dao.ClassDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController{
    
    @Autowired
    StudentDao stuDao;

    @Autowired
    ClassDao clsDao;

    @GetMapping("/students/{serialNumber}")
    public String student_detail (@PathVariable int serialNumber, Model m){
        m.addAttribute("student", stuDao.getStudentBySerialNumber(serialNumber));
        m.addAttribute("classes", clsDao.getAllClasses());
        return "student_detail";
    }

    @PostMapping("/students/{serialNumber}/edit")
    public String student_update(@ModelAttribute Student student, @PathVariable int serialNumber, Model m) {
        student.setSerialNumber(serialNumber);
        // System.out.println(student.getDOB());
        stuDao.update(student);
        return "redirect:/students/"+student.getSerialNumber();
    }

    @GetMapping("/students/new")
    public String student_new(Model m){
        m.addAttribute("student",new Student());
        m.addAttribute("classes", clsDao.getAllClasses());
        return "new_student";
    }

    @PostMapping("/students/new")
    public String student_add(@ModelAttribute Student student, Model m){
        // System.out.println(student.getGender());
        int serialNumber = stuDao.save(student);
        // int serialNumber=1;
        m.addAttribute("classes", clsDao.getAllClasses());
        return "redirect:/students/"+serialNumber;
    }

    @GetMapping("/students/search")
    public String student_new(Model m){
        m.addAttribute("student",new Student());
        m.addAttribute("classes", clsDao.getAllClasses());
        return "new_student";
    }

}