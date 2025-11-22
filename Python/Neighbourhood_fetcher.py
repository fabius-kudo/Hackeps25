import requests
import json
import os

# Fetch data from Overpass API
def fetch_overpass(query):
    url = "https://overpass-api.de/api/interpreter"
    response = requests.get(url, params={"data": query})
    response.raise_for_status()
    return response.json()

#JSON folder
output_dir = "JSON"
os.makedirs(output_dir, exist_ok=True)

# Save JSON to file
def save_json(data, filename):
    filepath = os.path.join(output_dir, filename)
    with open(filepath, "w") as f:
        json.dump(data, f, indent=2)
    print(f"Saved {len(data)} items to {filepath}")


def main():
    # Los Angeles bounding box (south, west, north, east)
    bbox = "33.90,-118.50,34.30,-118.10"

    # Overpass query: nodes, ways, relations with place=suburb|neighbourhood|quarter
    query = f"""
    [out:json];
    (
      node["place"~"suburb|neighbourhood|quarter"]({bbox});
      way["place"~"suburb|neighbourhood|quarter"]({bbox});
      relation["place"~"suburb|neighbourhood|quarter"]({bbox});
    );
    out center;
    """

    print("Fetching neighborhoods from Overpass...")
    data = fetch_overpass(query)
    elements = data.get("elements", [])

    # 1️⃣ Save raw elements
    save_json(elements, "la_neighborhoods_raw.json")

    # 2️⃣ Extract names for clean JSON
    names = [el.get("tags", {}).get("name") for el in elements if el.get("tags", {}).get("name")]

    save_json(names, "la_neighborhoods_clean.json")

    # Optional: print first 5 names
    print("First 5 neighborhood names:")
    for name in names[:5]:
        print("-", name)

if __name__ == "__main__":
    main()
