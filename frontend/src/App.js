import './App.less';
import {Link, Router} from "@reach/router";
import LandingPage from "./modules/LandingPage/LandingPage";
import VideoPage from "./modules/VideoPage/VideoPage";
import {connect} from "react-redux";


function App() {
	return (
		<div className="app w-full h-full bg-yellow-100" id="app">
			<div className="mx-auto h-full">
			<div className="flex flex-col bg-red-500 h-full">
				<header className="p-2 bg-red-300">
					<Link to="/v">LocalTube</Link>
				</header>
				<div className="flex flex-grow grid grid-cols-12 bg-yellow-400 gap-2">
					<div className="col-span-9 bg-yellow-100 flex flex-col">

						<div className="bg-red-100">
							<Router>
								<LandingPage path="/"/>
								<VideoPage path="/v"/>
							</Router>
						</div>

						<div className="bg-blue-100 h-32">
							tags
						</div>

						<div className="bg-blue-400 h-64">
							description
						</div>

						<div className="bg-blue-200 flex-grow">
							comments
						</div>

					</div>
					<div className="col-span-3 bg-yellow-300">
						<div className="bg-yellow-600 p-2 flex">
							<div className="w-32">
								<img src="a.png" />
							</div>
							<div>
								<p>a.mp4</p>
								<p>Folder</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>


		</div>
	);
}

const mapStateToProps = state => {
	return {}
};

const mapDispatchToProps = dispatch => {
	return {}
}

export default connect(mapStateToProps, mapDispatchToProps)(App);
