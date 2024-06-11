function ButtonComponent({ label, onClick }) {
  return (
    <button className="button" onClick={onClick}>
      {label}
    </button>
  );
}

export default ButtonComponent;
