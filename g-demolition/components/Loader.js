import {Spinner} from "@blueprintjs/core";

const Loader = ({text}) => {
  return <section>
    <p>{text}</p>
    <Spinner />
  </section>
};

export default Loader;