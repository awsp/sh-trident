import '../styles/globals.css'
import {Alignment, Navbar} from "@blueprintjs/core";

function App({ Component, pageProps }) {
  return <main>
    <Navbar>
      <Navbar.Group align={Alignment.CENTER}>
        <Navbar.Heading>SH Trident</Navbar.Heading>
        <Navbar.Divider/>
        <a href="/" className="bp3-button bp3-minimal">Dashboard</a>
        <a href="/changelog" className="bp3-button bp3-minimal">Changelog</a>
      </Navbar.Group>
    </Navbar>
    <Component {...pageProps} />
  </main>
}

export default App
