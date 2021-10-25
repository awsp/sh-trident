import {MEDIA_PAUSE, MEDIA_PLAY, MEDIA_STOP} from "./media-player.action.h";

export const init = (media, timerWrapper, timerBar) => dispatch => {
  if (media && timerWrapper) {
    const timer = timerWrapper.querySelector('span');

    media.addEventListener('timeupdate', () => {
      let minutes = Math.floor(media.currentTime / 60);
      let seconds = Math.floor(media.currentTime - minutes * 60);
      let minuteValue;
      let secondValue;

      if (minutes < 10) {
        minuteValue = '0' + minutes;
      } else {
        minuteValue = minutes;
      }

      if (seconds < 10) {
        secondValue = '0' + seconds;
      } else {
        secondValue = seconds;
      }

      let mediaTime = minuteValue + ':' + secondValue;
      let barLength = timerWrapper.clientWidth * (media.currentTime/media.duration);

      timer.innerText = mediaTime;
      timerBar.style.width = barLength + 'px';
    });
  }
}


export const playPausedMedia = (media) => dispatch => {
  if (media.paused) {
    dispatch({type: MEDIA_PLAY});
    media.play();
  } else {
    dispatch({type: MEDIA_PAUSE});
    media.pause();
  }
};

export const stopMedia = (media) => dispatch => {
  dispatch({type: MEDIA_STOP});
  media.pause();
  media.currentTime = 0;
};

export const windForwardMedia = (media) => dispatch => {

};

export const windBackwardMedia = (media) => dispatch => {

};

export const loadMedia = (media, source) => dispatch => {

};

export const requestFullscreen = (media) => dispatch => {

};