import ProjectCard from "../../ProjectCard/ProjectCard";

const Project = () => {
  const projectCardData = [
    {
      link: "https://git.generalassemb.ly/zkapalka/personal_portfolio",
      image: "src/assets/portfolio.jpg",
      alt: "Picture of the word portfolio",
      projectName: "Personal Portfolio",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    },
    { 
      link: "https://git.generalassemb.ly/zkapalka/Week_4",
      image: "src/assets/shoe.jpeg",
      alt: "Collection of different shoes arranged in the color of a rainbow",
      projectName: "DEI JavaScript Project",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    },
    {
      link: "https://git.generalassemb.ly/zkapalka/week_6/tree/main/day_3",
      image: "src/assets/dictionary-words-grammar-education-8b6784-1024 (1).jpg",
      alt: "Pic of the definition of dictionary",
      projectName: "Dictionary Java App",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    },
    { 
      link: "https://git.generalassemb.ly/zkapalka/week_12_SpringProject",
      image: "src/assets/Back flex.jpg",
      alt: "Drawing of man flexing back",
      projectName: "Backend Spring Project",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    },
    {
      image: "https://via.placeholder.com/300x200",
      alt: "Project 5",
      projectName: "Placeholder",
      description: "Placeholder for upcoming React DEI project",
    },
    {
      image: "https://via.placeholder.com/300x200",
      alt: "Project 6",
      projectName: "Placeholder",
      description: "placeholder for upcoming capstone project",
    }
  ];
  return (
    <section className="projects" id="projects">
      <h2>My Projects</h2>
      <div className="project-grid">
        {projectCardData.map((project, index) => {
          return (
            <ProjectCard key={index} link={project.link} image={project.image} alt={project.alt} projectName={project.projectName} description={project.description}/>
          );
        })}
      </div>
    </section>
  );
};

export default Project;
