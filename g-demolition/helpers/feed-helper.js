export async function getFeeds(focusId) {
  const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/focus/${focusId}/feeds`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  });
  return response.json();
}

export async function createFeedFocus(form) {
  const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/focus`, {
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(form)
  });
  return response.json();
}

export async function deleteFocus(focusId) {
  const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/focus/${focusId}`, {
    method: 'delete',
  });
  return response.json();
}

export function formatDate(pubDate) {
  const date = new Date(pubDate);
  return date.toLocaleString();
}

export const DEFAULT_NODE = [0, 0];