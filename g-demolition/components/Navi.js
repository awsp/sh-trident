import {Alignment, Navbar} from "@blueprintjs/core";
import Link from "next/link";

const Navi = () => (
  <Navbar>
    <Navbar.Group align={Alignment.CENTER}>
      <Navbar.Heading>SH Trident</Navbar.Heading>
      <Navbar.Divider />
      <Link href="/">
        <a className="bp3-button bp3-minimal">Dashboard</a>
      </Link>
      <Link href="/subscription">
        <a className="bp3-button bp3-minimal">Subscription</a>
      </Link>
      <Link href="/about">
        <a className="bp3-button bp3-minimal">About</a>
      </Link>
    </Navbar.Group>
  </Navbar>
);

export default Navi;