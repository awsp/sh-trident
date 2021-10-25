import {
  MEDIA_LOAD,
  MEDIA_PAUSE,
  MEDIA_PLAY,
  MEDIA_STOP,
  MEDIA_WIND_BACKWARD,
  MEDIA_WIND_FORWARD,
  MEDIA_READY,
} from "../actions/media-player.action.h";

const initialState = {
  currentSrc: '',
  mediaTime: '00:00',
  isPlaying: false,
  speedScale: 1
};

export default (state = initialState, action) => {
  switch (action.type) {
    case MEDIA_PLAY:
      return {
        ...state,
        isPlaying: true
      }

    case MEDIA_PAUSE:
      return {
        ...state,
        isPlaying: false
      }

    case MEDIA_STOP:
      return {
        ...state,
        isPlaying: false,
        mediaTime: 0
      }

    case MEDIA_LOAD:
      return {
        ...state,
        isPlaying: false,
        mediaTime: 0,
        currentSrc: action.payload.src
      }

    case MEDIA_WIND_BACKWARD:
      return state;

    case MEDIA_WIND_FORWARD:
      return state;

    case MEDIA_READY:
      return state;

    default:
      return state;
  }
};