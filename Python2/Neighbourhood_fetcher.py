import requests
import json
import os

output_dir = "JSON"
os.makedirs(output_dir, exist_ok=True)

NH_FILE = os.path.join(output_dir, "la_neighbourhoods_raw.json")

def fetch_overpass(query):
    url = "https://overpass-api.de/api/interpreter"
    response = requests.get(url, params={"data": query})
    response.raise_for_status()
    return response.json()

def main():
    bbox = "33.90,-118.50,34.30,-118.10"

    query = f"""
    [out:json];
    (
      node["place"~"suburb|neighbourhood|quarter"]({bbox});
      way["place"~"suburb|neighbourhood|quarter"]({bbox});
      relation["place"~"suburb|neighbourhood|quarter"]({bbox});
    );
    out center;
    """

    print("Fetching all neighbourhoods in LA...")
    data = fetch_overpass(query)
    elements = data.get("elements", [])

    with open(NH_FILE, "w") as f:
        json.dump(elements, f, indent=2)

    print(f"Saved {len(elements)} neighbourhoods to {NH_FILE}")

def load_neighbourhoods():
    with open(NH_FILE, "r") as f:
        return json.load(f)
