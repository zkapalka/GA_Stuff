import "./Asides.css"

const Aside = () => {

    const skillData = [   {
        img: "src/assets/React-icon.svg" ,
        imgAlt: "React Logo Icon",
        name: "React",
    }, 
    {
        img: "src/assets/java.svg" ,
        imgAlt: "Java Logo Icon",
        name: "Java",
    },
    {
        img: "src/assets/Git_icon.svg" ,
        imgAlt: "Git Logo Icon",
        name: "Git",
    },
    {
        img: "src/assets/postgresql.svg" ,
        imgAlt: "PostreSQL Logo Icon",
        name: "PostreSQL",
    },
    {
        img: "src/assets/css-3.svg" ,
        imgAlt: "CSS Icon",
        name: "CSS",
    },{
        img: "src/assets/spring-3.svg" ,
        imgAlt: "Spring Logo Icon",
        name: "Spring",
    },
    {
        img: "src/assets/Barbell-rear-delt-row-2.png" ,
        imgAlt: "React Logo Icon",
        name: "Lifting Heavy Stuff",
    }

]

    
  return (
    <>
    
    <aside >
    <h3>Skills</h3>
      <section id="skill">
      { skillData.map((skill, index) => (
        <div className="skillInfo" key={index}>
            <img src={skill.img} alt={skill.imgAlt} />
            <p>{skill.name}</p>
        </div>
      )
    )
      }

     
      </section>
    </aside>
    </>
  );
};

export default Aside;
