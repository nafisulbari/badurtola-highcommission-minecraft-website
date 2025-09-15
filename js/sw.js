const CACHE_NAME = 'bhc-assets-v3';
const ASSET_URLS = [
'/image/bh-cover-image2.jpg',
'/image/logo.png',
'/image/season1/02.png',
'/image/season1/03.png',
'/image/season1/04.png',
'/image/season1/05.png',
'/image/season1/06.png',
'/image/season1/07.png',
'/image/season1/08.png',
'/image/season1/09.png',
'/image/season1/10.png',
'/image/season1/11.png',
'/image/season1/12.png',
'/image/season1/13.png',
'/image/season1/14.png',
'/image/season1/15.png',
'/image/season1/16.png',
'/image/season1/17.png',
'/image/season1/18.jpg',
'/image/season1/19.png',
'/image/season1/20.png',
'/image/season1/21.png',
'/image/season2/01.png',
'/image/season2/02.png',
'/image/season2/03.png',
'/image/season2/04.png',
'/image/season2/05.png',
'/image/season2/06.png',
'/image/season2/07.png',
'/image/season2/08.png'
];

self.addEventListener('install', (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => cache.addAll(ASSET_URLS))
  );
});

self.addEventListener('activate', (event) => {
  event.waitUntil(
    caches.keys().then((keys) => Promise.all(
      keys.map((k) => (k !== CACHE_NAME ? caches.delete(k) : null))
    ))
  );
});

self.addEventListener('fetch', (event) => {
  const req = event.request;
  const dest = req.destination;

  // handle images (and optionally videos) but do not cache partial responses (206)
  if (dest === 'image' || dest === 'video') {
    event.respondWith(
      caches.match(req).then((cached) => {
        const networkFetch = fetch(req).then((res) => {
          // if response is partial (206) or not ok, do not cache it
          if (!res || !res.ok || res.status === 206) {
            return res;
          }
          const resClone = res.clone();
          caches.open(CACHE_NAME).then((cache) => cache.put(req, resClone));
          return res;
        }).catch(() => cached);
        return cached || networkFetch;
      })
    );
  }
});