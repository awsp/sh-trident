import React, {useEffect, useRef} from 'react';
import {useSelector} from "react-redux";


import {library} from '@fortawesome/fontawesome-svg-core';
import {
	faBars,
	faExpand,
	faFastBackward,
	faFastForward,
	faPause,
	faPlay,
	faStop
} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

library.add(faPlay, faPause, faStop, faFastBackward, faFastForward, faExpand, faBars);

const MediaPlayer = (
	{
		playPausedMedia,
		stopMedia,
		windBackwardMedia,
		windForwardMedia,
		loadMedia,
		init,
	}
) => {
	const mediaRef = useRef();
	const sourceRef = useRef();
	const timerRef = useRef();
	const timerBarRef = useRef();
	const mediaTime = useSelector(state => state.media.mediaTime);
	const isPlaying = useSelector(state => state.media.isPlaying);

	useEffect(() => {
		if (mediaRef.current && timerRef.current && timerBarRef.current) {
			init(mediaRef.current, timerRef.current, timerBarRef.current);
		}
	}, [mediaRef, timerRef, timerBarRef]);


	return <div className="player">
		<video preload="metadata" ref={mediaRef}>
			<source src="data/a.mp4#t=0.1" type="video/mp4" ref={sourceRef}/>
		</video>
		<div className="controls bg-gray-800">
			<div className="h-full bg-blue-200 flex-grow timer-bar">
				<div className="inner-bar" ref={timerBarRef}/>
			</div>
			<div className="flex bg-gray-800">
				<button className="play py-1 px-2"
				        onClick={() => playPausedMedia(mediaRef.current)}
				>
					<FontAwesomeIcon icon={isPlaying ? 'pause' : 'play'}/>
				</button>
				<button className="stop py-1 px-2"
				        onClick={() => stopMedia(mediaRef.current)}
				>
					<FontAwesomeIcon icon="stop"/>
				</button>
				<button className="rwd py-1 px-2"
				        onClick={() => windBackwardMedia(mediaRef.current, sourceRef.current)}
				>
					<FontAwesomeIcon icon="fast-backward"/>
				</button>
				<button className="fwd py-1 px-2"
				        onClick={() => windForwardMedia(mediaRef.current, sourceRef.current)}
				>
					<FontAwesomeIcon icon="fast-forward"/>
				</button>

				<div className="timer flex-grow flex mx-2" ref={timerRef}>
					<span aria-label="timer" className="flex mx-3 text-center">{mediaTime ? mediaTime : '00:00'}</span>
				</div>

				<button className="p-1">
					<FontAwesomeIcon icon="bars"/>
				</button>

				<button className="p-1">
					<FontAwesomeIcon icon="expand"/>
				</button>
			</div>
		</div>
	</div>
};

export default MediaPlayer;