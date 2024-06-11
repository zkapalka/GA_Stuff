import "./ProjectCard.css"

const ProjectCard = ({link, image, alt, projectName, description }) => {
  return (
    <div className="project-card">
        <a href={link}>
      <img src={image} alt={alt} />
      </a>
      <h3>{projectName}</h3>
      <p>{description}</p>
    </div>
  );
};

export default ProjectCard;
