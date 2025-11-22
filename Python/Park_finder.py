import requests
import json
import os

# Ensure JSON folder exists
output_dir = "JSON"
os.makedirs(output_dir, exist_ok=True)

def fetch_overpass(query):
    """Fetch data from Overpass API given a query string."""
    url = "https://overpass-api.de/api/interpreter"
    response = requests.get(url, params={"data": query})
    response.raise_for_status()
    return response.json()

def save_elements(data, filename):
    """Save elements to JSON file in output_dir."""
    filepath = os.path.join(output_dir, filename)
    with open(filepath, "w") as f:
        json.dump(data, f, indent=2)
   # print(f"Saved {len(data)} items to {filepath}")

def print_first_n(elements, n):
    """Print the first n elements for inspection."""
    print(f"First {n} elements:")
    for i, el in enumerate(elements[:n], 1):
        print(f"{i}: {json.dumps(el, indent=2)}\n")

def get_parks_near(lat, lon, radius=1000, save_json=True, print_preview=True):
    """
    Fetch parks within a radius (meters) around a given latitude and longitude in LA.
    Returns the list of elements from Overpass API.
    """
    # Los Angeles bounding box (south, west, north, east)
    bbox = "34.00,-118.50,34.30,-118.10"

    # Overpass query with 'around' and bounding box restriction
    query = f"""
    [out:json];
    (
      node["leisure"="park"](around:{radius},{lat},{lon})({bbox});
      way["leisure"="park"](around:{radius},{lat},{lon})({bbox});
      relation["leisure"="park"](around:{radius},{lat},{lon})({bbox});
    );
    out center;
    """

    data = fetch_overpass(query)
    elements = data.get("elements", [])

    if save_json:
        filename = f"parks_{lat}_{lon}_{radius}m.json"
        save_elements(elements, filename)

    return elements
