export async function getFeeds(focusId) {
  const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE}/focus/${focusId}/feeds`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    }
  });
  return response.json();
}

export const DEFAULT_NODE = [0, 0];