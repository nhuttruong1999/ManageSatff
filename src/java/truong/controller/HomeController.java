/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truong.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import truong.model.User;
import truong.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import truong.model.Depart;
import truong.model.DepartDAO;
import truong.model.Record;
import truong.model.RecordDAO;
import truong.model.Staff;
import truong.model.StaffDAO;

/**
 *
 * @author macosx
 */
@Controller

public class HomeController {

    private User conuser;
    @Autowired
    public UserDAO daoAccount;
    @Autowired
    public StaffDAO daoStaff;
    @Autowired
    public DepartDAO daoDepart;
    @Autowired
    public RecordDAO daoRecord;
    
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    ServletContext context;

    public HomeController() {

    }

    // Đầu tiên chạy trang login
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(ModelMap model, @ModelAttribute("user") User acc) {
        String thongbao = "";
        try {
            model.addAttribute("requestlogin", acc.getUsername());
            User newuser = daoAccount.getByUsername(acc.getUsername());
            if (newuser.getPassword().equals(acc.getPassword())) {
                conuser = newuser;
                model.addAttribute("Account", daoAccount.getAll());
                model.addAttribute("tenuser", acc.getUsername());
                model.addAttribute("StatisticsTOP10", daoRecord.getTOP10());
                return "home";
            } else {
                thongbao = "Sai ten dang nhap hoac mat khau";
                model.addAttribute("thongbao", thongbao);
                return "login";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(ModelMap model) {
        if (conuser == null) {
            return "login";
        } else {
            model.addAttribute("tenuser", conuser.getUsername());
            model.addAttribute("StatisticsTOP10", daoRecord.getTOP10());
            
            return "home";
        }
        
    }

    // chuyển trang show account
    @RequestMapping("showAccount")
    public String showAccount(ModelMap model) {
        if (conuser == null) {
            return "login";
        } else {
            model.addAttribute("tenuser", conuser.getUsername());
            model.addAttribute("Account", daoAccount.getAll());
            return "showAccount";
        }

    }

    //thêm, xoá, sữa, tìm kiếm User account.
    @RequestMapping(value = "insertAccount", method = RequestMethod.GET)
    public String insertAccount(ModelMap model) {
        model.addAttribute("user", new User());
        return "insertAccount";
    }

    @RequestMapping(value = "insertAccount", method = RequestMethod.POST)
    public String insertAccount(ModelMap model, @ModelAttribute("user") User acc, @RequestParam("image") MultipartFile image) throws IOException {
        String filehinhPath = context.getRealPath("/images/") + image.getOriginalFilename();
        image.transferTo(new File(filehinhPath));
        model.addAttribute("name", image.getOriginalFilename());
        acc.setImg(image.getOriginalFilename());
        daoAccount.insert(acc);
        model.addAttribute("thongtin", filehinhPath);
        model.addAttribute("Account", daoAccount.getAll());
        return "showAccount";

    }

    @RequestMapping(value = "updateAccount", method = RequestMethod.GET)
    public String updateAccount(ModelMap model, @RequestParam("laythongtin") String username, @ModelAttribute("user") User acc) {
        model.addAttribute("user", new User());
        model.addAttribute("RO", "true");
        model.addAttribute("user1", daoAccount.getByUsername(username));
        return "updateAccount";
    }

    @RequestMapping(value = "updateAccount", method = RequestMethod.POST)
    public String updateAccount(ModelMap model, @ModelAttribute("user") User acc, @RequestParam("image") MultipartFile image) {
        try {
            String filename = "";
            if (image.isEmpty()) {
                filename = daoAccount.getByUsername(acc.getUsername()).getImg();
            } else {
                filename = image.getOriginalFilename();
                String filehinhPath = context.getRealPath("/images/") + filename;
                image.transferTo(new File(filehinhPath));
            }
            model.addAttribute("images", image.getOriginalFilename());
            acc.setImg(filename);
            daoAccount.update(acc);
            model.addAttribute("Account", daoAccount.getAll());
            return "showAccount";

        } catch (Exception e) {
            model.addAttribute("message", "loi file!");
        }
        return "showAccount";
    }

    @RequestMapping(value = "deleteAccount", method = RequestMethod.GET)
    public String xoathongtinAccount(ModelMap model, @RequestParam("xoathongtin") String username) {
        daoAccount.delete(username);
        model.addAttribute("Account", daoAccount.getAll());
        return "showAccount";
    }

    @RequestMapping(value = "deleteallAccount", method = RequestMethod.POST)
    public String xoanhieuAccount(HttpServletRequest request, ModelMap model) {
        String[] list = request.getParameterValues("select");

        for (String username : list) {
//            User userclass=new User(user,"select");
            daoAccount.delete(username);
        }
//        
        model.addAttribute("Account", daoAccount.getAll());
        return "showAccount";
    }

    @RequestMapping(value = "findAccount", method = RequestMethod.POST)
    public String findAccount(ModelMap model, @ModelAttribute("username") String username) {
        model.addAttribute("Account", daoAccount.getByName(username));
        return "showAccount";
    }

    // chuyển trang show phòng ban
    @RequestMapping(value = "showDepart", method = RequestMethod.GET)
    public String showDepart(ModelMap model) {
        if (conuser == null) {
            return "login";
        } else {
            model.addAttribute("tenuser", conuser.getUsername());
            model.addAttribute("Depart", daoDepart.getAll());
            return "showDepart";
        }
    }

    @RequestMapping(value = "insertDepart", method = RequestMethod.GET)
    public String insertDepart(ModelMap model) {
        model.addAttribute("depart", new Depart());
        return "insertDepart";
    }

    @RequestMapping(value = "insertDepart", method = RequestMethod.POST)
    public String insertDepart(ModelMap model, @ModelAttribute("depart") Depart dp, @RequestParam("image") MultipartFile image) {
        try {
            String filehinhPath = context.getRealPath("/images/") + image.getOriginalFilename();
        image.transferTo(new File(filehinhPath));
        model.addAttribute("name1", image.getOriginalFilename());
        dp.setDepartimg(image.getOriginalFilename());
        daoDepart.insertDepart(dp);
        model.addAttribute("thongtin", filehinhPath);
        model.addAttribute("Depart", daoDepart.getAll());
        model.addAttribute("thongbaoDepart", "thanh cong ne!");
        } catch (Exception e) {
            model.addAttribute("thongbaoDepart", "Nhap thieu thong tin dau vao hoac sai dinh dang");
        }
        return "showDepart";
    }

    @RequestMapping(value = "updateDepart", method = RequestMethod.GET)
    public String updateDepart(ModelMap model, @RequestParam("thongtinDepart") String departid) {
        model.addAttribute("depart", new Depart());
        model.addAttribute("R1", "true");
        model.addAttribute("depart1", daoDepart.getDepartid(departid));
        model.addAttribute("value", daoDepart.getDepartid(departid).getGg());
        return "updateDepart";
    }

    @RequestMapping(value = "updateDepart", method = RequestMethod.POST)
    public String updateDepart(ModelMap model, @ModelAttribute("depart") Depart dp, @RequestParam("image") MultipartFile image) {
        try {
            String filename = "";
            if (image.isEmpty()) {
                filename = daoDepart.getDepartid(dp.getDepartid()).getDepartimg();
            } else {
                filename = image.getOriginalFilename();
                String filehinhPath = context.getRealPath("/images/") + filename;
                image.transferTo(new File(filehinhPath));
            }
            model.addAttribute("images", image.getOriginalFilename());
            dp.setDepartimg(filename);
            daoDepart.updateDepart(dp);
            model.addAttribute("Depart", daoDepart.getAll());
            return "showDepart";

        } catch (Exception e) {
            model.addAttribute("message", "loi file!");
        }
        return "showDepart";
    }

    @RequestMapping(value = "deleteDepart", method = RequestMethod.GET)
    public String xoathongtinDepart(ModelMap model, @RequestParam("xoathongtinDepart") String departid) {
        daoDepart.deleteDepart(departid);
        model.addAttribute("Depart", daoDepart.getAll());
        return "showDepart";
    }

    @RequestMapping(value = "deleteallDepart", method = RequestMethod.POST)
    public String xoanhieuDepart(HttpServletRequest request, ModelMap model) {
        String[] list = request.getParameterValues("select");
        for (String departid : list) {
//            User userclass=new User(user,"select");
            daoDepart.deleteDepart(departid);
        }
        model.addAttribute("Depart", daoDepart.getAll());
        return "showDepart";
    }

    @RequestMapping(value = "findDepart", method = RequestMethod.POST)
    public String findDepart(ModelMap model, @ModelAttribute("Departid") String departid) {
        model.addAttribute("Depart", daoDepart.getByDepartID(departid));
        return "showDepart";
    }

    // chuyển trang show Nhân viên
    @RequestMapping(value = "showStaff", method = RequestMethod.GET)
    public String showStaff(ModelMap model) {
        if (conuser == null) {
            return "login";
        } else {
            model.addAttribute("tenuser", conuser.getUsername());
            model.addAttribute("Staff", daoStaff.getAll());
            return "showStaff";
        }
    }

    @RequestMapping(value = "insertStaff", method = RequestMethod.GET)
    public String insertStaff(ModelMap model) {
        model.addAttribute("staff", new Staff());
        return "insertStaff";
    }

    @RequestMapping(value = "insertStaff", method = RequestMethod.POST)
    public String insertStaff(ModelMap model, @ModelAttribute("staff") Staff st, @RequestParam("image") MultipartFile image) throws IOException {
        String filehinhPath = context.getRealPath("/images/") + image.getOriginalFilename();
        image.transferTo(new File(filehinhPath));
        model.addAttribute("name2", image.getOriginalFilename());
        st.setPhoto(image.getOriginalFilename());
        daoStaff.insertStaff(st);
        model.addAttribute("thongtin", filehinhPath);
        model.addAttribute("Staff", daoStaff.getAll());
        return "showStaff";

    }

    @RequestMapping(value = "updateStaff", method = RequestMethod.GET)
    public String updateStaff(ModelMap model, @RequestParam("thongtinStaff") String staffid) {
        model.addAttribute("staff", new Staff());
        model.addAttribute("R2", "true");
        model.addAttribute("staff1", daoStaff.getByStaffid(staffid));
        model.addAttribute("value", daoStaff.getByStaffid(staffid).getSuagt());
        model.addAttribute("value1", daoStaff.getByStaffid(staffid).getSuatt());
        return "updateStaff";
    }

    @RequestMapping(value = "updateStaff", method = RequestMethod.POST)
    public String updateStaff(ModelMap model, @ModelAttribute("staff") Staff st, @RequestParam("image") MultipartFile image) {
        try {
            String filename = "";
            if (image.isEmpty()) {
                filename = daoStaff.getByStaffid(st.getStaffid()).getPhoto();
            } else {
                filename = image.getOriginalFilename();
                String filehinhPath = context.getRealPath("/images/") + filename;
                image.transferTo(new File(filehinhPath));
            }
            model.addAttribute("images", image.getOriginalFilename());
            st.setPhoto(filename);
            daoStaff.updateStaff(st);
            model.addAttribute("Staff", daoStaff.getAll());
            return "showStaff";
        } catch (Exception e) {
            model.addAttribute("message", "loi file!");
        }
        return "showStaff";
    }

    @RequestMapping(value = "deleteStaff", method = RequestMethod.GET)
    public String xoathongtinStaff(ModelMap model, @RequestParam("xoathongtinStaff") String staffid) {
        daoStaff.deleteStaff(staffid);
        model.addAttribute("Staff", daoStaff.getAll());
        return "showStaff";
    }

    @RequestMapping(value = "deleteallStaff", method = RequestMethod.POST)
    public String xoanhieuStaff(HttpServletRequest request, ModelMap model) {
        String[] list = request.getParameterValues("select");
        for (String staffid : list) {
//            User userclass=new User(user,"select");
            daoStaff.deleteStaff(staffid);
        }
        model.addAttribute("Staff", daoStaff.getAll());
        return "showStaff";
    }

    @RequestMapping(value = "findStaff", method = RequestMethod.POST)
    public String findStaff(ModelMap model, @ModelAttribute("Staffid") String staffid) {
        model.addAttribute("Staff", daoStaff.getByStaffname(staffid));
        return "showStaff";
    }

    //chuyen trang show thanh tich
    @RequestMapping(value = "showStatistics", method = RequestMethod.GET)
    public String showStatistics(ModelMap model) {
        if (conuser == null) {
            return "login";
        } else {
            model.addAttribute("tenuser", conuser.getUsername());
            model.addAttribute("Statistics", daoRecord.getAll());
            return "showStatistics";
        }
    }

    @RequestMapping(value = "insertStatistics", method = RequestMethod.GET)
    public String insertStatistics(ModelMap model) {
        model.addAttribute("statistics", new Record());
        return "insertStatistics";
    }

    @RequestMapping(value = "insertStatistics", method = RequestMethod.POST)
    public String insertStatistics(ModelMap model, @ModelAttribute("statistics") Record rc) {
        daoRecord.insertRecord(rc);
        model.addAttribute("Statistics", daoRecord.getAll());
        return "showStatistics";
    }

    @RequestMapping(value = "updateStatistics", method = RequestMethod.GET)
    public String updateStatistics(ModelMap model, @RequestParam("thongtinStatistics") String recordid) {
        model.addAttribute("statistics", new Record());
        model.addAttribute("R3", "true");
        model.addAttribute("statistics1", daoRecord.getByRecordID(recordid));
        model.addAttribute("value", daoRecord.getByRecordID(recordid).getType());
        return "updateStatistics";
    }

    @RequestMapping(value = "updateStatistics", method = RequestMethod.POST)
    public String updateStatistics(ModelMap model, @ModelAttribute("statistics") Record rc) {

        try {
            daoRecord.updateRecord(rc);
            model.addAttribute("Statistics", daoRecord.getAll());
            return "showStatistics";
        } catch (Exception e) {
            model.addAttribute("alo",e.toString());
            return "showStatistics";
        }

    }

    @RequestMapping(value = "deleteStatistics", method = RequestMethod.GET)
    public String xoathongtinStatistics(ModelMap model, @RequestParam("xoathongtinStatistics") int recordid) {
        daoRecord.deleteRecord(recordid);
        model.addAttribute("Statistics", daoRecord.getAll());
        return "showStatistics";
    }

    @RequestMapping(value = "deleteallStatistics", method = RequestMethod.POST)
    public String xoanhieuStatistics(HttpServletRequest request, ModelMap model) {
        String[] list = request.getParameterValues("select");
        for (String recordid : list) {
//            User userclass=new User(user,"select");
            daoRecord.deleteRecord(Integer.parseInt(recordid));
        }
        model.addAttribute("Statistics", daoRecord.getAll());
        return "showStatistics";
    }

    @RequestMapping(value = "findStatistics", method = RequestMethod.POST)
    public String findStatistics(ModelMap model, @ModelAttribute("RecordID") String staffid) {
        model.addAttribute("Statistics", daoRecord.getsearchStaffID(staffid));
        return "showStatistics";
    }
    
    // chuyen trang show top10
    
    @RequestMapping(value = "showStatisticsTOP10", method = RequestMethod.GET)
    public String showStatisticsTOP10(ModelMap model) {
        if (conuser == null) {
            return "login";
        } else {
            model.addAttribute("tenuser", conuser.getUsername());
            model.addAttribute("StatisticsTOP10", daoRecord.getTOP10());
            
            return "showStatisticsTOP10";
        }
    }
    
    
    // button send mail
    @RequestMapping(value = "SendmailStatistics")
     public String emailing(ModelMap m, @ModelAttribute("id") int id) {
        try {
            MimeMessage thisemail = mailSender.createMimeMessage();
            MimeMessageHelper help = new MimeMessageHelper(thisemail, true);
            help.setFrom("truonglnps11031@fpt.edu.vn");
            help.setTo(daoStaff.getByStaffid(daoRecord.getByRecordID(id).getStaffID()).getEmail());
            help.setReplyTo("truonglnps11031@fpt.edu.vn");
            help.setSubject("THONG BAO THANH TICH");
            help.setText("<h1>JAVA 5</h1>"
                    + "<h2>THÔNG BÁO KHEN THƯỞNG / KỶ LUẬT</h2>"
                    + "<h3>Nhân viên: " + daoStaff.getByStaffid(daoRecord.getByRecordID(id).getStaffID()).getStaffname() + " </h3>"
                    + "<h4>Nội dung: <span style=\"color: red\">" + daoRecord.getByRecordID(id).getSuaType() + "</span></h4>"
                    + "<h4>Lý do: <span style=\"color: red\">" + daoRecord.getByRecordID(id).getReason() + "</span></h4>"
                    + "<h4>Ngày lưu thành tích/kỷ luật: " + daoRecord.getByRecordID(id).getRecordDate() + "</h4>", true);
            mailSender.send(thisemail);
            m.addAttribute("thongbao", "Gửi email thành công!");
            m.addAttribute("Statistics", daoRecord.getAll());
        } catch (Exception e) {
            m.addAttribute("thongbao", e.toString());
        }
        return "showStatistics";
    }
    
}
