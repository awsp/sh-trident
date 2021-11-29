export function generateEpisode(ep, data) {
  const arr = [];
  const padding = ep.toString().split('').length;

  for (let i = 1, j = 0; i <= ep; i++, j++) {
    arr.push(data && data[j] ? data[j] : {
      ep: i.toString().padStart(padding, '0'),
      summary: '',
      remarks: '',
      done: false
    })
  }
  return arr;
}