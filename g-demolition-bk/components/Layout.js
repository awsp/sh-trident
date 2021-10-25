import Head from "next/head";
import Navi from "./Navi";

const Layout = ({children}) => (
  <>
    <Head>
      <title>SH Trident</title>
    </Head>
    <Navi/>
    {children}
  </>
);

export default Layout;