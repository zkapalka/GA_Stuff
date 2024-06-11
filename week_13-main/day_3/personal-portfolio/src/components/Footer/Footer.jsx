import List from "../List/List";
import "./Footer.css"

const Footer = () => {
  const listObjects = [
    {href: "https://twitter.com/?lang=en", name: "X", desc: "X link"},
    {href: "https://www.linkedin.com/", name: "LinkedIn", desc: "Linkedin link"},
    {href: "https://git.generalassemb.ly/", name: "GitHub", desc: "GitHub link"},
  ]

  return (
    <footer id="contact">
      <div className="footer-content">
        <div className="footer-section">
          <h4>Contact</h4>
          <p>Email: zkapalka@indeed.com</p>
          <p>Phone: 123-456-7890</p>
        </div>
        <div className="footer-section">
          <h4>Social</h4>
          <ul>
            {
              listObjects.map((list, index) => <List key={index} name={list.name} href={list.href} desc={list.desc}/>
              )
            }
          </ul>
        </div>
      </div>
      <div className="footer-bottom">
        <p>&copy; 2024 My Portfolio. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer