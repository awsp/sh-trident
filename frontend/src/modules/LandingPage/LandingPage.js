import "video.js/dist/video-js.css";
import {
	init,
	loadMedia,
	playPausedMedia,
	stopMedia,
	windBackwardMedia,
	windForwardMedia
} from "../../store/actions/media-player.action";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import MediaPlayerV2 from "../../components/MediaPlayer/MediaPlayerV2";
import {useRef} from "react";

const LandingPage = () => {
	const playerRef = useRef(null);
	const videoJsOptions = { // lookup the options in the docs for more options
		autoplay: false,
		controls: true,
		responsive: true,
		fluid: true,
		playbackRates: [0.25, 0.5, 0.75, 1, 1.25, 1.5, 1.75, 2],
		userActions: {
			hotkeys: true
		},
		inactivityTimeout: 10,
		sources: [{
			src: '/data/a.mp4',
			type: 'video/mp4'
		}]
	};

	const handlePlayerReady = (player) => {
		playerRef.current = player;

		// you can handle player events here
		player.on('waiting', () => {
			console.log('player is waiting');
		});

		player.on('dispose', () => {
			console.log('player will dispose');
		});
	};

	const change = () => {
		playerRef.current.src({
			type: 'video/mp4',
			src: '/data/subfolder/d.mp4'
		});
	};

	return <>
		Landing Page
		<MediaPlayerV2 options={videoJsOptions} onReady={handlePlayerReady}/>
		<button onClick={change}>d.mp4</button>
	</>
};

const mapStateToProps = state => {
	return {
		media: state.media
	}
};

const mapDispatchToProps = dispatch => {
	return {
		playPausedMedia: bindActionCreators(playPausedMedia, dispatch),
		stopMedia: bindActionCreators(stopMedia, dispatch),
		windForwardMedia: bindActionCreators(windForwardMedia, dispatch),
		windBackwardMedia: bindActionCreators(windBackwardMedia, dispatch),
		loadMedia: bindActionCreators(loadMedia, dispatch),
		init: bindActionCreators(init, dispatch),
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(LandingPage);