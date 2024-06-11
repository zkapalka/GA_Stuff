const List = ({href, name, desc}) => {
    return (
      <li>
        <p>{desc}</p>
        <a href={href}>{name}</a>
      </li>
    );
  };
  
  export default List;
  
  // const props = {
  //   href: "",
  //   name: ""
  // }
  
  // const {href, name} = props