package com.example.contact;
import org.springframework.ui.Model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    //This annotation allows Spring to automatically inject an instance of the NoteRepository into the MainController class, enabling you to use the repository's methods to interact with the database.

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ContactRepository contactRepository;
    
    @GetMapping("/")
        public String StartBody(Model model){
            return "index";
        }

    @GetMapping("/userpage")
        public String StartBody(HttpSession session, Model model){
            String uid = session.getAttribute("uid").toString();
            return "userpage";
        }

    @GetMapping("/mycontacts")
        public String mycontacts(HttpSession session, Model model){
            String uid = session.getAttribute("uid").toString();
            return "mycontacts";
        }

        @GetMapping("/welcome")
        public String welcome(HttpSession session, Model model){
            String uid = session.getAttribute("uid").toString();
            return "welcome";
        }

        @GetMapping("/note")
        public String note(HttpSession session, Model model){
            String uid = session.getAttribute("uid").toString();
            return "note";
        }
        @GetMapping("/mapnote")
        public String mapnote(HttpSession session,@RequestParam int id, Model model){
            model.addAttribute("pid", id);
            Contact p=contactRepository.findById(id).get();
            model.addAttribute("ptitle", p.getId());
            String uid = session.getAttribute("uid").toString();
            return "mapnote";
        }

        @GetMapping("/getphone")
        public @ResponseBody Optional<Contact> getPhone(@RequestParam int id) {
            return contactRepository.findById(id);
        }



        @PostMapping("/login")
        public @ResponseBody String login(HttpSession session, @RequestParam String email, @RequestParam String password) {

            try{
                User u= userRepository.findByEmailAndPassword(email, password);
                session.setAttribute("uid",u.getId()+"");
                return "1";
            }
            catch(Exception e){
                return "0";
            }
            
        }
        

        @PostMapping("/signup") 
        public @ResponseBody String signup(@RequestParam String fullname, @RequestParam String password,@RequestParam String email ) {
          
          User u=new User();
          u.setEmail(email);
          u.setPassword(password);
          u.setFullname(fullname);
          try {
          userRepository.save(u);
          return "1";
          }
          catch(Exception e)
          {
              return "0";	
          }
          
        }

        @PostMapping("/addphone") 
        public @ResponseBody String signup(HttpSession session, @RequestParam String fullname,@RequestParam String email, @RequestParam String phone
                , @RequestParam String address, @RequestParam String comment) {
          String uid = session.getAttribute("uid").toString();
          int id= Integer.parseInt(uid);
          User u = userRepository.findById(id).get();
          Contact p =new Contact();
          p.setFullname(fullname);
          p.setEmail(email);
          p.setAddress(address);
          p.setPhone(phone);
          p.setComment(comment);
          p.setUsr(u);
          try {
          contactRepository.save(p);
          return "1";
          }
          catch(Exception e)
          {
              return "0";	
          }
          
        }

        @PostMapping("/addnote") 
        public @ResponseBody String addNote(HttpSession session, @RequestParam String date, @RequestParam String title, @RequestParam String content, @RequestParam Integer contact_id) {
        String uid = session.getAttribute("uid").toString();
        int id = Integer.parseInt(uid);
        User u = userRepository.findById(id).orElse(null);
        if (u != null) {
            Note note = new Note();
            note.setDate(date);
            note.setTitle(title);
            note.setContent(content);
            Contact contact = new Contact();
            contact.setId(contact_id);
            note.setContact(contact); // Set the associated contact

            try {
                noteRepository.save(note);
                return "1";
            } catch (Exception e) {
                return "0";
            }
        } else {
            return "0";
        }
    }


    @PostMapping("/addmapnote") 
        public @ResponseBody String addMapNote(HttpSession session, @RequestParam String date, @RequestParam String title, @RequestParam String content, @RequestParam String lat, @RequestParam String lng, @RequestParam Integer contact_id) {
        String uid = session.getAttribute("uid").toString();
        int id = Integer.parseInt(uid);
        User u = userRepository.findById(id).orElse(null);
        if (u != null) {
            Note note = new Note();
            note.setDate(date);
            note.setTitle(title);
            note.setContent(content);
            note.setLat(lat);
            note.setLng(lng);
            Contact contact = new Contact();
            contact.setId(contact_id);
            note.setContact(contact); // Set the associated contact

            try {
                noteRepository.save(note);
                return "1";
            } catch (Exception e) {
                return "0";
            }
        } else {
            return "0";
        }
    }

        @GetMapping("/getuser")
        public @ResponseBody Optional <User> getuser(HttpSession session){
            String uid = session.getAttribute("uid").toString();
            try{
                int id= Integer.parseInt(uid);
                return userRepository.findById(id);
            }
            catch(Exception e){
                System.out.println("+++++++/getuser"+e);
                return null;
            }
            
        }

        @GetMapping("/getphones")
        public @ResponseBody Iterable<Contact> getphones(HttpSession session){
            try{
                String uid = session.getAttribute("uid").toString();
                Integer id= Integer.parseInt(uid);
                User u = userRepository.findById(id).get();
                return contactRepository.findAllByUsr(u);
            }
            catch(Exception e){
                System.out.println("/getphones +++++++++++++++++ "+e);
                return null;
            }
            
        }

        @GetMapping("/getnotes")
        public @ResponseBody Iterable<Note> getnotes(HttpSession session, @RequestParam Integer id) {
            try {
                String uid = session.getAttribute("uid").toString();
                Integer userId = Integer.parseInt(uid);
                User user = userRepository.findById(userId).get();
                Contact contact = user.getContacts().stream()
                        .filter(c -> c.getId().equals(id))
                        .findFirst()
                        .orElse(null);
                if (contact != null) {
                    return noteRepository.findAllByContact(contact);
                } else {
                    // Handle the case when the contact with the given ID is not found
                    return null;
                }
            } catch (Exception e) {
                System.out.println("/getnotes +++++++++++++++++ " + e);
                return null;
            }
        }
        



        @GetMapping("/logout")
        public @ResponseBody String logout(HttpSession session){
            session.removeAttribute("uid");
            return "0";
        }





}


