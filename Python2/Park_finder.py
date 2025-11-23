import requests
import json
import os
import math

output_dir = "JSON"
os.makedirs(output_dir, exist_ok=True)

PARKS_FILE = os.path.join(output_dir, "la_parks_raw.json")

def fetch_overpass(query):
    url = "https://overpass-api.de/api/interpreter"
    response = requests.get(url, params={"data": query})
    response.raise_for_status()
    return response.json()

def haversine(lat1, lon1, lat2, lon2):
    R = 6371e3  # meters
    phi1, phi2 = math.radians(lat1), math.radians(lat2)
    dphi = math.radians(lat2 - lat1)
    dlambda = math.radians(lon2 - lon1)

    a = math.sin(dphi/2)**2 + math.cos(phi1)*math.cos(phi2)*math.sin(dlambda/2)**2
    return R * 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))

def fetch_all_parks_LA():
    bbox = "33.90,-118.50,34.30,-118.10"

    query = f"""
    [out:json];
    (
      node["leisure"="park"]({bbox});
      way["leisure"="park"]({bbox});
      relation["leisure"="park"]({bbox});
    );
    out center;
    """

    print("Fetching all parks in LA...")
    data = fetch_overpass(query)
    elements = data.get("elements", [])

    with open(PARKS_FILE, "w") as f:
        json.dump(elements, f, indent=2)

    print(f"Saved {len(elements)} parks to {PARKS_FILE}")

def load_parks():
    with open(PARKS_FILE, "r") as f:
        return json.load(f)

def count_parks_within_radius(lat, lon, radius_m):
    parks = load_parks()
    count = 0

    for p in parks:
        center = p.get("center")
        if not center:
            continue

        plat = center.get("lat")
        plon = center.get("lon")

        if haversine(lat, lon, plat, plon) <= radius_m:
            count += 1

    return count
